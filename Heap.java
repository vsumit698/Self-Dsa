import java.util.*;

public class Heap {
    ArrayList<Integer> list;
    String heapType="min";
    public Heap(String heapType){
        this.list = new ArrayList<>();
        if(heapType.equals("max")){
            this.heapType = heapType;
        }
    }
    public boolean isEmpty(){
        return this.size() == 0;
    }
    public int size(){return this.list.size();}
    public void heapify(int rootId){
        int childId = this.getProperChildId(rootId);
        while(rootId != childId){
            this.swap(rootId,childId);
            rootId = childId;
            childId = this.getProperChildId(rootId);
        }
    }
    public int getProperChildId(int rootId){
        int leftChildId = this.leftChildId(rootId),rightChildId = this.rightChildId(rootId),leftChildValue, rightChildValue;

        if(leftChildId >= this.size() && rightChildId >= this.size()) {
            // rootId is leaf node
            return rootId;
        }
        if(this.heapType.equals("min")){
            leftChildValue = Integer.MAX_VALUE;
            rightChildValue = Integer.MAX_VALUE;
            if(leftChildId<this.size()) leftChildValue = this.list.get(leftChildId);
            if(rightChildId<this.size()) rightChildValue = this.list.get(rightChildId);

            // setting rootId to min value id -> rootId or leftChildId or rightChildId
            if(leftChildValue < this.list.get(rootId)) rootId = leftChildId;
            if(rightChildValue < this.list.get(rootId)) rootId = rightChildId;
        }else if(this.heapType.equals("max")){
            leftChildValue = Integer.MIN_VALUE;
            rightChildValue = Integer.MIN_VALUE;
            if(leftChildId<this.size()) leftChildValue = this.list.get(leftChildId);
            if(rightChildId<this.size()) rightChildValue = this.list.get(rightChildId);

            // setting rootId to max value id -> rootId or leftChildId or rightChildId
            if(leftChildValue > this.list.get(rootId)) rootId = leftChildId;
            if(rightChildValue > this.list.get(rootId)) rootId = rightChildId;
        }
        return rootId;
    }

    public void setKeyFromDownToTop(int rootId){
        int parentId = this.getProperParentId(rootId);
        while(rootId != parentId){
            this.swap(rootId,parentId);
            rootId = parentId;
            parentId = this.getProperParentId(rootId);
        }
    }
    public int getProperParentId(int rootId){
        int parentId = this.parentId(rootId);
        if(this.heapType.equals("min") && this.list.get(parentId)>this.list.get(rootId)){
            rootId = parentId;
        }else if(this.heapType.equals("max") && this.list.get(parentId)<this.list.get(rootId)){
            rootId = parentId;
        }
        return rootId;
    }

    public void insert(int key){
        this.list.add(key);
        this.setKeyFromDownToTop(this.size()-1);
    }
    public int delete(int index) throws Exception{
        if(index >= this.size()){
            throw new Exception("Invalid Index");
        }
        int deletedValue = this.list.get(index);

        if(this.heapType.equals("min")) this.list.set(index,Integer.MIN_VALUE);
        else this.list.set(index,Integer.MAX_VALUE);

        this.setKeyFromDownToTop(index);// this will take deleted id to root of tree
        this.extractPriority();

        return deletedValue;
    }
    public int extractPriority() throws Exception{
        if(this.isEmpty()){
            throw new Exception("Empty Heap");
        }
        int removedKey = this.list.get(0);
        this.list.set(0,this.list.get(this.size()-1));
        this.list.remove(this.size()-1);

        this.heapify(0);
        return removedKey;
    }
    public int getPriority() throws Exception {
        if(this.isEmpty()){
            throw new Exception("Empty Heap");
        }
        return this.list.get(0);
    }
    public int parentId(int rootId){
        return (rootId-1)/2;
    }
    public int leftChildId(int rootId){
        return 2*rootId+1;
    }
    public int rightChildId(int rootId){
        return 2*rootId+2;
    }
    public void swap(int id1,int id2){
        int temp = this.list.get(id1);
        this.list.set(id1,this.list.get(id2));
        this.list.set(id2,temp);
    }

    public static void main(String[] args) throws Exception {
        Heap heap = new Heap("max");
        heap.insert(12);
        heap.insert(11);
        heap.insert(8);
        heap.insert(7);
        heap.insert(6);
        System.out.println(heap.extractPriority());
        System.out.println(heap.list);
        heap.delete(1);
        heap.delete(0);
        System.out.println(heap.getPriority());
        System.out.println(heap.size());
    }
}
