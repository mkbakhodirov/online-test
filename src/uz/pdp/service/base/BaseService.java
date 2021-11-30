package uz.pdp.service.base;

public interface BaseService<T, S> {
    void add(T t);
    T get(String str1, String str2);
    T getByIndex(int index);
    T get(S s);
    boolean check(String str1);
    boolean isEmpty();
}
