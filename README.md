# antlr语法节点查找器

功能概述
=============================
        基于类似JQuery选择器语法的DSL(叫AQuery)实现对Antlr生成的语法树的节点批量查询功能


步骤解析
==============================
        1. 输入AQuery查询语句
        2. 生成AQuery语法树
        3. 解析AQuery语法树生成多步骤多条查询指令
        4. 输入待被查询的Antlr语法树，在语法树上流式地执行一系列查询指令
        5. 响应输出查找到的目标语法节点
        
示例
==============================
        querySpecification:is(>selectItem>identifier:text(\w+)):is( *:not(subqueryRelation) tableName:text(vipdm.*+))
        解释:
        querySpecification 为按照节点名叫querySpecification为按照节点名叫的进行查找
        :is() 为以is之前查询的节点为根、按照括号内的查询语句进行后续查找 查找结果不为空时保留:is之前的筛选到的节点
        > 为相邻子节点查找
        :not()__ 与 :is() 相反
        :text()__ 为节点 文本内容的匹配筛选 可以应用正则匹配
        空格 为全部子孙节点的查找
        * 为任意节点都可以匹配的一个占位符
        + 相邻后继节点
        ~ 所有后继兄弟节点