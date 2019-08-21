# language: en
Feature: 语法树转SQL

  @ToSql
  Scenario Outline: TO_SQL
    Given 输入SQL"<>sql"
    Then 生成语法树
    Then TO_SQL"<result>"

    Examples:
      | sql | result |
      | 9   | 9      |


  @ReplaceToSql
  Scenario Outline: TO_SQL
    Given 输入SQL"<sql>"
    Then 生成语法树
    Then 表名替换
    Then TO_SQL"<result>"

    Examples:
      | sql | result |
      | 10   | 10      |

  @AppendWhereToSql
  Scenario Outline: TO_SQL
    Given 输入SQL"<sql>"
    Then 生成语法树
    Then 追加条件
    Then TO_SQL"<result>"

    Examples:
      | sql | result |
      | 11   | 11      |