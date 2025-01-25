public class Warehouse<T> {
    String type, name;
    T object;

    public void setName(String name) {
        this.name = name;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String[] getInformation() {
        String[] a = { type, name };
        return a;
    }

    public T getObject() {
        return object;
    }
}
