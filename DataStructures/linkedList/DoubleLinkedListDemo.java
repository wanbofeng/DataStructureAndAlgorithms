package com.linkedList;

/**
 * Created by wanbf on 2019/6/2.        双向列表
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        //进行测试
        //先创建节点
        StuNode2 stu1 = new StuNode2(1, "张三", "85");
        StuNode2 stu2 = new StuNode2(2, "李四", "87");
        StuNode2 stu3 = new StuNode2(3, "小明", "70");
        StuNode2 stu4 = new StuNode2(4, "小红", "90");

        //创建要给链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        //加入
        doubleLinkedList.addByOrder(stu1);
        doubleLinkedList.addByOrder(stu4);
        doubleLinkedList.addByOrder(stu3);
        doubleLinkedList.addByOrder(stu2);
        System.out.println("原始双链表数据  " );
        doubleLinkedList.list();


    }

}
class DoubleLinkedList{
    StuNode2 head = new StuNode2(0,"","");
    //返回头节点
    public StuNode2 getHead() {
        return head;
    }
    public  void addByOrder(StuNode2 stuNode2){
        StuNode2 temp = head;
        boolean flag = false;
        while (true){
            if (temp.next == null){
                break;
            }
            if (temp.next.stuNo>stuNode2.stuNo){//位置找到
                break;
            }else if (temp.stuNo == stuNode2.stuNo){
                flag=true;
                break;
            }
            temp =temp.next;
        }
        if (flag){
            System.out.printf("准备插入的学生 %d 已经存在了, 不能加入\n", stuNode2.stuNo);
        }else{
            stuNode2.next=temp.next;
            temp.next=stuNode2;
            stuNode2.prior=temp;
            if(temp.next != null){
                temp.next.prior=stuNode2;
            }
        }
    }
    // 添加一个节点到双向链表的最后.
    public void  add(StuNode2 stuNode2){

        // 因为head节点不能动，因此我们需要一个辅助遍历 temp
        StuNode2 temp = head;
        // 遍历链表，找到最后
        while (true) {
            // 找到链表的最后
            if (temp.next == null) {//
                break;
            }
            // 如果没有找到最后, 将将temp后移
            temp = temp.next;
        }
        // 当退出while循环时，temp就指向了链表的最后
        // 形成一个双向链表
        temp.next = stuNode2;
        stuNode2.prior = temp;
    }

    // 从双向链表中删除一个节点,
    // 说明
    // 1 对于双向链表，我们可以直接找到要删除的这个节点
    // 2 找到后，自我删除即可
    public void del(int stuNo) {

        // 判断当前链表是否为空
        if (head.next == null) {// 空链表
            System.out.println("链表为空，无法删除");
            return;
        }

        StuNode2 temp = head.next; // 辅助变量(指针)
        boolean flag = false; // 标志是否找到待删除节点的
        while (true) {
            if (temp == null) { // 已经到链表的最后
                break;
            }
            if (temp.stuNo == stuNo) {
                // 找到的待删除节点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next; // temp后移，遍历
        }
        // 判断flag
        if (flag) { // 找到
            // 可以删除
            // temp.next = temp.next.next;[单向链表]
            temp.prior.next = temp.next;
            // 如果是最后一个节点，就不需要执行下面这句话，否则出现空指针
            if (temp.next != null) {
                temp.next.prior = temp.prior;
            }
        } else {
            System.out.printf("要删除的 %d 节点不存在\n", stuNo);
        }
    }




    //遍历双向列表的方法   和单项列表一致
    //显示链表[遍历]
    public void list() {
        //判断链表是否为空
        if(head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头节点，不能动，因此我们需要一个辅助变量来遍历
        StuNode2 temp = head.next;
        while(true) {
            //判断是否到链表最后
            if(temp == null) {
                break;
            }
            //输出节点的信息
            System.out.println(temp);
            //将temp后移， 一定小心
            temp = temp.next;
        }
    }
    // 修改一个节点的内容, 可以看到双向链表的节点内容修改和单向链表一样
    // 只是 节点类型改成 StuNode2
    public void update(StuNode2 newStuNode) {
        // 判断是否空
        if (head.next == null) {
            System.out.println("链表为空~");
            return;
        }
        // 找到需要修改的节点, 根据stuNo编号
        // 定义一个辅助变量
        StuNode2 temp = head.next;
        boolean flag = false; // 表示是否找到该节点
        while (true) {
            if (temp == null) {
                break; // 已经遍历完链表
            }
            if (temp.stuNo == newStuNode.stuNo) {
                // 找到
                flag = true;
                break;
            }
            temp = temp.next;
        }
        // 根据flag 判断是否找到要修改的节点
        if (flag) {
            temp.name = newStuNode.name;
            temp.mark = newStuNode.mark;
        } else { // 没有找到
            System.out.printf("没有找到 学号 %d 的节点，不能修改\n", newStuNode.stuNo);
        }
    }





}


//定义StuNode2 ， 定义StuNode2 对象就是一个节点
class StuNode2 {
    public int stuNo;
    public String name;
    public String mark;
    public StuNode2 next; //指向下一个节点
    public StuNode2 prior;//指向前一个节点
    //构造器
    public StuNode2(int stuNo, String name, String mark) {
        this.stuNo = stuNo;
        this.name = name;
        this.mark = mark;
    }
    //为了显示方法，我们重新toString
    @Override
    public String toString() {
        return "StuNode [stuNo=" + stuNo + ", name=" + name + ", mark=" + mark + "]";
    }
}