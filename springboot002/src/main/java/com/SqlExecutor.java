package com;

import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class SqlExecutor implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        // 删除 zixun 表中名为“1”的孤立宠物医生记录
        String sql = "DELETE FROM zixun WHERE chongwuyisheng = '1'"; 
        try {
            jdbcTemplate.execute(sql);
            System.out.println(">>>> SQL 执行成功！已删除名为 1 的宠物医生咨询记录。");
        } catch (Exception e) {
            System.err.println(">>>> SQL 执行失败: " + e.getMessage());
        }
    }
}
