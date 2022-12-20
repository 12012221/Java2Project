package com.example.springproject.github;

import com.example.springproject.config.Druid;
import com.example.springproject.domain.Issue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

public class issuesPipeline implements Pipeline {
    public static Connection con = null;
    public static PreparedStatement githubRepo_stmt = null;
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public Lock balanceChangeLock = new ReentrantLock();

    public static void openDB(String dbname) {
        try {
            //
            Class.forName("org.postgresql.Driver");
        } catch (Exception e) {
            System.err.println("Cannot find the Mysql driver. Check CLASSPATH.");
            System.exit(1);
        }

        try {
            con = Druid.getConnection();
            con.setAutoCommit(false);
        } catch (SQLException e) {
            System.err.println("Database connection failed");
            System.err.println(e.getMessage());
            System.exit(1);
        }


        try {
            githubRepo_stmt = con.prepareStatement("insert into issue values(?,?,?,?,?,?,?,?)");
        } catch (SQLException e) {
            System.err.println("Insert statement failed");
            System.err.println(e.getMessage());
            closeDB();
            System.exit(1);
        }
    }

    public static void commit() {
        try {
            con.commit();
        } catch (SQLException throwables) {
            System.err.println("SQL error: " + throwables.getMessage());
            try {
                con.rollback();
            } catch (Exception e2) {
            }
            closeDB();
            System.exit(1);
        }
    }

    public static void closeDB() {
        if (con != null) {
            try {
                if (githubRepo_stmt != null) {
                    githubRepo_stmt.close();
                }
                Druid.closeAll(con);
            } catch (Exception e) {
                // Forget about it
            }
        }
    }

    public static void load_Githubrepo(Issue tmp) {
        try {
            if (con != null) {
                githubRepo_stmt.setLong(1, tmp.getId());
                githubRepo_stmt.setString(2, tmp.getRepoName());
                githubRepo_stmt.setObject(3, tmp.getCreate_time());
                githubRepo_stmt.setObject(4, tmp.getClose_time());
                githubRepo_stmt.setString(5, tmp.getComment());
                githubRepo_stmt.setString(6, tmp.getDescription());
                githubRepo_stmt.setString(7, tmp.getTitle());
                githubRepo_stmt.setString(8, tmp.getStatus());
                githubRepo_stmt.addBatch();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        balanceChangeLock.lock();
        try {
            openDB("cs209_project");
            List<Issue> issues = resultItems.get("issues");
            issues.stream().forEach(o -> load_Githubrepo(o));
            try {
                githubRepo_stmt.executeBatch();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            commit();
            closeDB();
        } finally {
            balanceChangeLock.unlock();
        }

    }
}
