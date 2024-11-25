package hw2.task2;

import java.lang.reflect.Field;
import java.util.UUID;

public class QueryBuilder {
    public String buildInsertQuery(Object obj) throws IllegalAccessException {
        Class<?> clazz = obj.getClass();
        StringBuilder query = new StringBuilder("INSERT INTO ");

        if (clazz.isAnnotationPresent(Table.class)) {
            Table tableAnnotation = clazz.getAnnotation(Table.class);
            query
                    .append(tableAnnotation.name())
                    .append(" ( ");
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Column.class)) {
                    Column columnAnnotation = field.getAnnotation(Column.class);
                    query.append(columnAnnotation.name()).append(", ");
                }
            }
            if (query.charAt(query.length() - 2) == ',') {
                query.delete(query.length() - 2, query.length() - 1);
            }

            query.append(") VALUES ( ");
            for (Field field : fields) {
                if (field.isAnnotationPresent(Column.class)) {
                    field.setAccessible(true);
                    query.append("'").append( field.get(obj)).append("', ");
                }
            }
            if (query.charAt(query.length() - 2) == ',') {
                query.delete(query.length() - 2, query.length() - 1);
            }

            query.append(" );");
            return query.toString();
        } else
            return null;
    }

    public String buildSelectQuery(Class<?> clazz, UUID primaryKey) throws IllegalAccessException {
        StringBuilder query = new StringBuilder("SELECT * FROM ");

        if (clazz.isAnnotationPresent(Table.class)) {
            Table tableAnnotation = clazz.getAnnotation(Table.class);
            query.append(tableAnnotation.name()).append(" WHERE ");
        }

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                Column columnAnnotation = field.getAnnotation(Column.class);
                if (columnAnnotation.primaryKey()) {
                    query.append(field.getName()).append(" = ").append(primaryKey).append(";");
                    return query.toString();
                }
            }
        }

        return null;
    }

    public String buildUpdateQuery(Object obj) throws IllegalAccessException {
        Class<?> clazz = obj.getClass();
        StringBuilder query = new StringBuilder("UPDATE ");

        if (clazz.isAnnotationPresent(Table.class)) {
            Table tableAnnotation = clazz.getAnnotation(Table.class);
            query
                    .append(tableAnnotation.name())
                    .append(" SET ");
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Column.class)) {
                    Column columnAnnotation = field.getAnnotation(Column.class);
                    if (!columnAnnotation.primaryKey()) {
                        field.setAccessible(true);
                        query.append(columnAnnotation.name()).append(" = '")
                                .append(field.get(obj)).append("', ");
                    }
                }
            }
            if (query.charAt(query.length() - 2) == ',') {
                query.delete(query.length() - 2, query.length() - 1);
            }

            query.append(" WHERE ");

            for (Field field : fields) {
                if (field.isAnnotationPresent(Column.class)) {
                    Column columnAnnotation = field.getAnnotation(Column.class);
                    if (columnAnnotation.primaryKey()) {
                        field.setAccessible(true);
                        query.append(field.getName()).append(" = ").append(field.get(obj)).append(";");
                        return query.toString();
                    }
                }
            }


            query.append(" );");
            return query.toString();
        } else
            return null;
    }

    /**
     * TODO: Доработать в рамках домашней работы!
     * ```sql
     * DELETE FROM Customers
     * WHERE Country = 'Germany';
     * ```
     * @param clazz
     * @param primaryKey
     * @return
     */
    public String buildDeleteQuery(Class<?> clazz, UUID primaryKey) {
        StringBuilder query = new StringBuilder("DELETE FROM ");

        if (clazz.isAnnotationPresent(Table.class)) {
            Table tableAnnotation = clazz.getAnnotation(Table.class);
            query.append(tableAnnotation.name()).append(" WHERE ");
        }

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                Column columnAnnotation = field.getAnnotation(Column.class);
                if (columnAnnotation.primaryKey()) {
                    field.setAccessible(true);
                    query.append(field.getName()).append(" = ").append(primaryKey).append(";");
                    return query.toString();
                }
            }
        }

        return null;
    }
}
