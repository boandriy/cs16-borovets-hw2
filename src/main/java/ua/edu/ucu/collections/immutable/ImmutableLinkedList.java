package ua.edu.ucu.collections.immutable;

public class ImmutableLinkedList implements ImmutableList{
    private final Node root;

    public ImmutableLinkedList(){
        root = new Node(null);
    }

    private ImmutableLinkedList(Node root){
        this.root = root;
    }

    class Node {
        public Node next;
        public Node previous;
        public Object value;

        public Node(Object value) {
            this.previous = previous;
            this.value = value;
        }

        public void setNext(Node n) {
            this.next = n;
        }

        public void setPrevious(Node n) {
            this.previous = n;
        }

        public Node lastNode(){
            Node x = new Node(null);
            while(hasNext()){
                x = next;
            }

            return x;
        }

        public boolean hasNext() {
            if (this.next != null) {
                return true;
            } else {
                return false;
            }
        }
    }



    @Override
    public ImmutableList add(Object e) {
        if (root.value == null) {
            Node root = new Node(e);
        } else {
            if(root.hasNext()) {
                Node root = new Node(this.root.value);
                root.setNext(this.root.next);
                root.next.setPrevious(root);
                root.lastNode().next = new Node(e);
                root.lastNode().setPrevious(this.root.lastNode());
            }else{
                Node root = new Node(this.root.value);
                root.next = new Node(e);
                root.next.setPrevious(root);
            }
        }
        return new ImmutableLinkedList(root);
    }

    @Override
    public ImmutableList add(int index, Object e) {
        if(index < size()){
            Node root = new Node(this.root.value);
            root.setNext(this.root.next);
            root.next.setPrevious(root);
            Node x = root;
            for(int i = 0;i<size();i++){
                x = x.next;
                if(i == index){
                    x.previous.setNext(new Node(e));
                    x.previous.next.setPrevious(x.previous);
                }
            }
        } else {
            throw new IndexOutOfBoundsException("size =" + size() + "  index =" + index);
        }

        return new ImmutableLinkedList(root);
    }

    @Override
    public ImmutableList addAll(Object[] c) {
        Node root = new Node(this.root.value);
        root.setNext(this.root.next);
        root.next.setPrevious(root);
        Node x =root.lastNode();
        for(int i =0;i<c.length;i++){
            x.next = new Node(c[i]);
        }
        return new ImmutableLinkedList(root);
    }

    @Override
    public ImmutableList addAll(int index, Object[] c) {
        Node root = new Node(this.root.value);
        root.setNext(this.root.next);
        root.next.setPrevious(root);
        if(index < size()){
            Node x = root;
            int count = 0;
            for(int i =0;i<size();i++){
                if(i == index){
                    x.setNext(new Node(c[count]));
                    count++;
                    x = x.next;
                    continue;
                }
                x = x.next;
            }
        }else{
            throw new IndexOutOfBoundsException("size = "+size() + ", index = "+ index);
        }
        return new ImmutableLinkedList(root);
    }

    @Override
    public Object get(int index) {
        if(index < size()){
            Node n = root;
            for(int i=0;i<=size();i++){
                if(i == index){
                    return n.value;
                }
                n=n.next;
            }
        }else{
            throw new IndexOutOfBoundsException("size = "+size()+ ",  index ="+index );
        }
        return null;
    }

    @Override
    public ImmutableList remove(int index) {
        if(index >= size()){
            throw new IndexOutOfBoundsException("sieze = "+ size()+", index = " + index);
        }
        Node root = new Node(this.root.value);
        root.setNext(this.root.next);
        root.next.setPrevious(root);
        Node n = root;
        for(int i=0;i<size();i++){
            if(i == index){
                n.previous.setNext(n.next);
                n.next.setPrevious(n.previous);
                break;
            }
            n = n.next;
        }

        return new ImmutableLinkedList(root);
    }

    @Override
    public ImmutableList set(int index, Object e) {
        if(index >= size()){
            throw new IndexOutOfBoundsException("size = "+ size() + ", index = "+ index);
        }else{
            Node root = new Node(this.root.value);
            root.setNext(this.root.next);
            root.next.setPrevious(root);
            Node n = root;
            for(int i=0;i<size();i++){
                if(i == index){
                    n.previous.setNext(new Node(e));
                    n.next.next.setPrevious(n.previous);
                    break;
                }
                n = n.next;
            }
        }
        return new ImmutableLinkedList(root);
    }

    @Override
    public int indexOf(Object e) {
        Node n = root;
        for(int i=0;i<size();i++){
            if(n.value == e){
                return i;
            }
            n = n.next;
        }
        return 0;
    }

    @Override
    public int size() {
        Node n = root;
        int length = 0;
        while(n.hasNext()){
            length++;
        }
        return length;
    }

    @Override
    public ImmutableList clear() {
        Node root = new Node(null);
        return new ImmutableLinkedList(root);
    }

    @Override
    public boolean isEmpty() {
       if(root.value != null){
           return false;
       }
        return true;
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size()];
        Node n = root;
        for(int i=0;i<size();i++){
            arr[i] = n.value;
            n=n.next;
        }
        return arr;
    }

    public  ImmutableLinkedList addFirst(Object e){
        Node root = new Node(e);
        root.next = this.root.next;
        root.next.setPrevious(root);
        return new ImmutableLinkedList(root);
    }

    public ImmutableLinkedList addLast(Object e){
        Node root = new Node(this.root.value);
        root.setNext(this.root.next);
        root.next.setPrevious(root);
        root.lastNode().next = new Node(e);
        root.lastNode().previous = this.root.lastNode();
        return new ImmutableLinkedList(root);
    }

    public Object getFirst(){
        return root.value;
    }

    public Object getLast(){
        return root.lastNode().value;
    }

    public ImmutableLinkedList removeFirst(){

        return (ImmutableLinkedList) this.remove(0);
    }

    public ImmutableLinkedList removeLast(){
        return (ImmutableLinkedList) this.remove(size()-1);

    }
}
