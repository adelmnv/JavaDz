package kz.itstep.annotations;

public class AnnotationService {
    public String getTableName(Class<?> clazz){
        Table table = clazz.getAnnotation(Table.class);
        if(table!=null){
            return table.value();
        }
        else {
            throw new RuntimeException("Table doesn't gave annotation");
        }
    }
}
