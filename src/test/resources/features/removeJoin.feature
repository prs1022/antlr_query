# language: en
Feature: 删除无用join辅表测试

  @RemoveJoin
  Scenario Outline: 删除测试
    Given 输入SQL"<dsl>"
    Then 输出结果"<result>"

    Examples:
      | dsl      | result   |
      | remove_1 | remove_1 |
      | remove_2 | remove_2 |
      | remove_3 | remove_3 |