###java 泛型学习  
#####java 通用泛型类型  
```properties
E 元素   
K key   
V value   
T  类型  

```
实际例子  
```java
public interface Collection<E> extends Iterable<E> {} 
collection 使用 E  

public interface Map<K, V> {}  
map 使用的K,V

public final class Class<T> implements java.io.Serializable,
                              GenericDeclaration,
                              Type,
                              AnnotatedElement {}    
class 使用的是 T  
传入两个入参对象 T U    
传入三个对象类型 T U R  
@FunctionalInterface
public interface BiFunction<T, U, R> {}  

以上就是泛型参数的使用  
  
```