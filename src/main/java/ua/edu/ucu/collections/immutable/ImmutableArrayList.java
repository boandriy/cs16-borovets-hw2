package ua.edu.ucu.collections.immutable;

public class ImmutableArrayList implements  ImmutableList{
    private final Object[] arr;

    private ImmutableArrayList(Object[] arr) {
        this.arr = arr;
    }

    public ImmutableArrayList() {
        this.arr = new Object[0];
    }


    @Override
    public ImmutableList add(Object e){
        Object[] arr = new Object[size() + 1];
        for(int i=0;i<size();i++){
            arr[i] = this.arr[i];
        }
        arr[size()] = e;
        return new ImmutableArrayList(arr);
    }

    @Override
    public ImmutableList add(int index, Object e) {
        Object[] arr = new Object[size()+1];
        if(index == size()){
            arr[index] = e;
        }else {
            if (index < size()) {
                for (int i = 0; i < size(); i++) {
                    arr[i] = this.arr[i];
                }
                for (int i = size() - 2; i >= index; i--) {
                    arr[i + 1] = arr[i];
                }
                arr[index] = e;
            } else {
                throw new IndexOutOfBoundsException("size =" + size() + "  index =" + index);
            }
        }
        return new ImmutableArrayList(arr);
    }

    @Override
    public ImmutableList addAll(Object[] c) {
        Object[] arr = new Object[size()+c.length];
        int count = 0;
        for(int i =0;i<size();i++){
            arr[i] = this.arr[i];
            count++;
        }
        for(int i=0;i<c.length;i++){
            arr[count] = c[i];
        }
        return new ImmutableArrayList(arr);
    }

    @Override
    public ImmutableList addAll(int index, Object[] c) {
        Object[] arr = new Object[size()+c.length];
        int count = 0;
        if(index < size()){
            for(int i=0;i<size();i++){
                arr[i] = this.arr[i];
            }
            for(int i=index;i<size();i++){
                if(arr[i] != null){
                    count ++;
                }
            }
            Object[] temp = new Object[count];
            count = 0;
            for(int i=index;i<size();i++){
                temp[count] = arr[i];
                arr[i]= null;
                count++;
            }
            for(int i = arr.length- temp.length; i<arr.length; i++){
                arr[i] = temp[count];
                count++;
            }
            count = 0;
            for(int i=0;i<arr.length;i++){
                if(arr[i]==null){
                    arr[i] = temp[count];
                    count++;
                }
            }
        }else{
            throw new IndexOutOfBoundsException("size = "+size() + ", index = "+ index);
        }
        return new ImmutableArrayList(arr);
    }

    @Override
    public Object get(int index) {
        if(index < size()){
            return arr[index];
        }else{
            throw new IndexOutOfBoundsException("size = "+size()+ ",  index ="+index );
        }
    }

    @Override
    public ImmutableList remove(int index) {
        if(index >= size()){
            throw new IndexOutOfBoundsException("sieze = "+ size()+", index = " + index);
        }

        Object[] arr = new Object[size()-1];

        if(index==size()-2){
            for(int i =0;i<size()-2;i++){
                arr[i] = this.arr[i];
            }
        }else{
            int count = 0;
            for(int i =0;i<size()-2;i++){
                if(i != index){
                    arr[count] = this.arr[i];
                    count++;
                }
            }
        }
        return new ImmutableArrayList(arr);
    }

    @Override
    public ImmutableList set(int index, Object e) {
        if(index >= size()){
            throw new IndexOutOfBoundsException("size = "+ size() + ", index = "+ index);
        }else{
            Object[] arr = new Object[size()];
            arr[index] = e;
        }
        return new ImmutableArrayList(arr);
    }

    @Override
    public int indexOf(Object e) {
        for(int i = 0;i<size();i++){
            if(arr[i].equals(e)){
                return i;
            }
        }
        return 0;
    }

    @Override
    public int size() {
        return arr.length;
    }

    @Override
    public ImmutableList clear() {
        Object[] arr = new Object[0];
        return new ImmutableArrayList(arr);
    }

    @Override
    public boolean isEmpty() {
        for(Object i : arr){
            if(i != null){
                return false;
            }
        }
        return true;
    }

    @Override
    public Object[] toArray() {
        return arr;
    }
}
