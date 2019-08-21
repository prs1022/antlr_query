# language: en
Feature: 查询测试

  @Query
  Scenario Outline: 查询测试
    Given 输入DSL"<dsl>"
    And 输入SQL"<sql>"
    Then 查找节点
    Then 查找结果为"<result>"

    Examples:
      | dsl | sql | result |
      | 1   | 1   | 1      |
#      | 2   | 2   | 2      |
#      | 3   | 3   | 3      |
#      | 4   | 4   | 4      |
#      | 5   | 5   | 5      |
#      | 6   | 6   | 6      |
#      | 7   | 7   | 7      |
#      | 4   | 8   | 8      |