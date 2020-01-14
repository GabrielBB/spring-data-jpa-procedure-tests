
# spring-data-jpa @Procedure with ResultSets Testing Project

In [this fork](https://github.com/GabrielBB/spring-data-jpa) I made a change to spring-data-jpa project so you can called procedures that return ResultSets and REF_CURSORs with the @Procedure annotation. This change was tested using this project with MySQL, Postgres, Oracle and SQL Server databases. Every database has its own Integration Tests since in MySQL you can return ResultSets without REF_CURSORs but in Oracle it is mandatory to use REF_CURSOR to return ResultSets. 

What did I do to make it work for any of those 4 databases? I added a new parameter called "refCursor" to express that the procedure in your database is using a REF_CURSOR. Here is an Example that would work with Oracle:

```java
@Procedure("my_procedure",  refCursor = true)
List<Entity> myProcedure(Integer input);
```

However if you're using MySQL or SQL Server, you don't need that parameter. This will work:

```java
@Procedure("my_procedure")
List<Entity> myProcedure(Integer input);
```

You can also return a single Entity from your procedure. For Example:

```java
@Procedure("my_procedure")
Entity myProcedure();
```

One more... You can also return a list of generic objects. For example:

```java
@Procedure("my_procedure")
List<Object[]> myProcedure();
```

Nothing else is needed to call your procedure with ResultSets. I made sure you don't have to use Hibernate's @Namedstoredprocedurequery in your entities, making them dirty. I also made sure you can still return regular output parameters. This still works:

```java
@Procedure("my_procedure")
Integer myProcedure();
```


Okay... So if I use MySQL, how do you know that my procedure is returning a ResultSet if I don't use the refCursor parameter? [Here is the answer](https://docs.oracle.com/javaee/7/api/javax/persistence/StoredProcedureQuery.html#execute--). I coded thinking first about databases that don't support REF_CURSOR but do support ResultSets, when I made sure that @Procedure is finally able to return ResultSets, then I introduced the refCursor parameter.

How is the ResultSet parsed to the method return type, for example, an Entity? I check if your method's return type is an Entity (don't worry, i re-used some logic used by @Query), if it is, then I pass the class type to [StoredProcedureQuery](https://docs.oracle.com/javaee/7/api/javax/persistence/StoredProcedureQuery.html) when instantiating it, if not, then I omit the class type.

To return ResultSets the code calling the @Procedure method needs to have an active transaction. I re-used some logic I found to check if no transaction is active, if not, then I throw [InvalidDataAccessApiUsageException](https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/dao/InvalidDataAccessApiUsageException.html)


I added 6 unit tests covering the following cases:

```java
        @Procedure("0_input_1_row_resultset")
        Dummy singleEntityFromResultSetAndNoInput();

        @Procedure("1_input_1_row_resultset")
        Dummy singleEntityFrom1RowResultSetWithInput(Integer arg);

        @Procedure("0_input_1_resultset")
        List<Dummy> entityListFrom1RowResultSetWithNoInput();

        @Procedure("1_input_1_resultset")
        List<Dummy> entityListFrom1RowResultSetWithInput(Integer arg);

        @Procedure(value = "1_input_1_resultset", outputParameterName = "dummies")
        List<Dummy> entityListFrom1RowResultSetWithInputAndNamedOutput(Integer arg);

        @Procedure(value = "1_input_1_resultset", outputParameterName = "dummies", refCursor = true)
        List<Dummy> entityListFrom1RowResultSetWithInputAndNamedOutputAndCursor(Integer arg);

```
