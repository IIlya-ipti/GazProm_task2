package engine;



import java.util.List;

public record SortList<T extends ImageInterface>(List<T> list) {
    public SortList(List<T> list) {
        this.list = list;
    }

    public void add(T obj) {
        list.add(obj);
    }


    public List<T> getList() {
        return list;
    }

    public T getObject(double X, double Y){
        for( T obj : list){
            if(obj.contains(X,Y)){
                return obj;
            }
        }
        return null;
    }


}
