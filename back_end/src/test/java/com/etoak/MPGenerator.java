package com.etoak;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.enums.SqlLike;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.LikeTable;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.jupiter.api.Test;


public class MPGenerator {



//    @Test
    public void generate() {
        AutoGenerator generator = new AutoGenerator();

        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(System.getProperty("user.dir") + "/src/main/java");
        globalConfig.setAuthor("ET2211");
        globalConfig.setOpen(false);
        globalConfig.setFileOverride(true); //重新生成时文件是否覆盖
        globalConfig.setServiceName("%sService"); //去掉Service接口的首字母I
        globalConfig.setIdType(IdType.AUTO); //主键策略
        globalConfig.setDateType(DateType.ONLY_DATE); //定义生成的实体类中日期类型
        // globalConfig.setBaseResultMap(true); // mapper映射文件中生成 ResultMap
        // globalConfig.setBaseColumnList(true); // mapper映射文件中生成 基本字段
        generator.setGlobalConfig(globalConfig);

        // 数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/et2211?serverTimezone=UTC");
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("123456");
        dataSourceConfig.setDbType(DbType.MYSQL);
        generator.setDataSource(dataSourceConfig);

        // 4、包配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.etoak");
        packageConfig.setController("controller");
        packageConfig.setEntity("entity");
        packageConfig.setService("service");
        packageConfig.setMapper("mapper");
        packageConfig.setXml("mapper"); // 映射文件
        generator.setPackageInfo(packageConfig);

        // 5、策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel); // 数据库表映射到实体的命名
//        strategy.setTablePrefix("exam_"); //设置表前缀不生成
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);//数据库表字段映射
        strategy.setRestControllerStyle(true); //restful api风格控制器
        strategy.setControllerMappingHyphenStyle(false); // url中驼峰转连字符
//        strategy.setLikeTable(new LikeTable("exam_", SqlLike.RIGHT)); // "t_%"
//        strategy.setInclude("exam_questions");

        strategy.setEntityLombokModel(true);
        generator.setStrategy(strategy);
        generator.execute();
    }

}
