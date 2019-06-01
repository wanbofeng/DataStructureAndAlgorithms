package com.linkedList;

import java.util.Stack;

public class SingleLinkedListDemo {

	public static void main(String[] args) {
		//进行测试
		//先创建节点
		StuNode hero1 = new StuNode(1, "张三", "85");
		StuNode hero2 = new StuNode(2, "李四", "87");
		StuNode hero3 = new StuNode(3, "小明", "70");
		StuNode hero4 = new StuNode(4, "小红", "90");

		//创建要给链表
		SingleLinkedList singleLinkedList = new SingleLinkedList();


		//加入
		singleLinkedList.addByOrder(hero1);
		singleLinkedList.addByOrder(hero4);
		singleLinkedList.addByOrder(hero2);
		singleLinkedList.addByOrder(hero3);

		// 测试一下单链表的反转功能
		System.out.println("原来链表的情况");
		singleLinkedList.list();
		System.out.println("逆序打印单链表");
		reversePrint(singleLinkedList.getHead());

		/*singleLinkedList.del(2);
		System.out.println("删除之后的链表");
		singleLinkedList.list();
		System.out.println("有效节点个数="+getLength(singleLinkedList.getHead()));
		StuNode stuNode = findLastIndexNode(singleLinkedList.getHead(),2);
		System.out.println("倒数第二个节点数是"+stuNode);*/
	}

	//查找单链表中的倒数第k个结点 【新浪面试题】
	//思路
	//1. 编写一个方法，接收head节点，同时接收一个index
	//2. index 表示是倒数第index个节点
	//3. 先把链表从头到尾遍历，得到链表的总的长度 getLength
	//4. 得到size 后，我们从链表的第一个开始遍历 (size-index)个，就可以得到
	//5. 如果找到了，则返回该节点，否则返回nulll
	public static StuNode findLastIndexNode(StuNode head, int index) {
		//判断如果链表为空，返回null
		if(head.next == null) {
			return null;//没有找到
		}
		//第一个遍历得到链表的长度(节点个数)
		int size = getLength(head);
		//第二次遍历  size-index 位置，就是我们倒数的第K个节点
		//先做一个index的校验
		if(index <=0 || index > size) {
			return null;
		}
		//定义给辅助变量， for 循环定位到倒数的index
		StuNode cur = head.next; //3 // 3 - 1 = 2
		for(int i =0; i< size - index; i++) {
			cur = cur.next;
		}
		return cur;
	}

	//方法：获取到单链表的节点的个数(如果是带头结点的链表，需求不统计头节点)
	/**
	 *
	 * @param head 链表的头节点
	 * @return 返回的就是有效节点的个数
	 */
	public static int getLength(StuNode head) {
		if(head.next == null) { //空链表
			return 0;
		}
		int length = 0;
		//定义一个辅助的变量, 这里我们没有统计头节点
		StuNode cur = head.next;
		while(cur != null) {
			length++;
			cur = cur.next; //遍历
		}
		return length;
	}
	//将单链表反转
	public static void reversetList(StuNode head) {
		//如果当前链表为空，或者只有一个节点，无需反转，直接返回
		if(head.next == null || head.next.next == null) {
			return ;
		}

		//定义一个辅助的指针(变量)，帮助我们遍历原来的链表
		StuNode cur = head.next;
		StuNode next = null;// 指向当前节点[cur]的下一个节点
		StuNode reverseHead = new StuNode(0, "", "");
		//遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表reverseHead 的最前端
		//动脑筋
		while(cur != null) {
			next = cur.next;//先暂时保存当前节点的下一个节点，因为后面需要使用
			cur.next = reverseHead.next;//将cur的下一个节点指向新的链表的最前端
			reverseHead.next = cur; //将cur 连接到新的链表上
			cur = next;//让cur后移
		}
		//将head.next 指向 reverseHead.next , 实现单链表的反转
		head.next = reverseHead.next;
	}
	//方式2：
	//可以利用栈这个数据结构，将各个节点压入到栈中，然后利用栈的先进后出的特点，就实现了逆序打印的效果
	public static void reversePrint(StuNode head) {
		if(head.next == null) {
			return;//空链表，不能打印
		}
		//创建要给一个栈，将各个节点压入栈
		Stack<StuNode> stack = new Stack<StuNode>();
		StuNode cur = head.next;
		//将链表的所有节点压入栈
		while(cur != null) {
			stack.push(cur);
			cur = cur.next; //cur后移，这样就可以压入下一个节点
		}
		//将栈中的节点进行打印,pop 出栈
		while (stack.size() > 0) {
			System.out.println(stack.pop()); //stack的特点是先进后出
		}
	}







}
//定义SingleLinkedList 管理我们的英雄
class SingleLinkedList {
	//先初始化一个头节点, 头节点不要动, 不存放具体的数据
	private StuNode head = new StuNode(0, "", "");


	//返回头节点
	public StuNode getHead() {
		return head;
	}

	//添加节点到单向链表
	//思路，当不考虑编号顺序时
	//1. 找到当前链表的最后节点
	//2. 将最后这个节点的next 指向 新的节点
	public void add(StuNode stuNode) {

		//因为head节点不能动，因此我们需要一个辅助遍历 temp
		StuNode temp = head;
		//遍历链表，找到最后
		while(true) {
			//找到链表的最后
			if(temp.next == null) {//
				break;
			}
			//如果没有找到最后, 将将temp后移
			temp = temp.next;
		}
		//当退出while循环时，temp就指向了链表的最后
		//将最后这个节点的next 指向 新的节点
		temp.next = stuNode;
	}
	//第二种方式在添加学生时，根据学号将学生插入到指定位置
	//(如果有这个学号，则添加失败，并给出提示)
	public void addByOrder(StuNode stuNode) {
		//因为头节点不能动，因此我们仍然通过一个辅助指针(变量)来帮助找到添加的位置
		//因为单链表，因为我们找的temp 是位于 添加位置的前一个节点，否则插入不了
		StuNode temp = head;
		boolean flag = false; // flag标志添加的编号是否存在，默认为false
		while(true) {
			if(temp.next == null) {//说明temp已经在链表的最后
				break; //
			}
			if(temp.next.stuNo > stuNode.stuNo) { //位置找到，就在temp的后面插入
				break;
			} else if (temp.next.stuNo == stuNode.stuNo) {//说明希望添加的stuNode的学号已然存在

				flag = true; //说明学号存在
				break;
			}
			temp = temp.next; //后移，遍历当前链表
		}
		//判断flag 的值
		if(flag) { //不能添加，说明学号存在
			System.out.printf("准备插入的学生 %d 已经存在了, 不能加入\n", stuNode.stuNo);
		} else {
			//插入到链表中, temp的后面
			stuNode.next = temp.next;
			temp.next = stuNode;
		}
	}
	//修改节点的信息, 根据stuNo来修改，即stuNo不能改.
	//说明
	//1. 根据 newStuNode 的 stuNo 来修改即可
	public void update(StuNode newStuNode) {
		//判断是否空
		if(head.next == null) {
			System.out.println("链表为空~");
			return;
		}
		//找到需要修改的节点, 根据stuNo
		//定义一个辅助变量
		StuNode temp = head.next;
		boolean flag = false; //表示是否找到该节点
		while(true) {
			if (temp == null) {
				break; //已经遍历完链表
			}
			if(temp.stuNo == newStuNode.stuNo) {
				//找到
				flag = true;
				break;
			}
			temp = temp.next;
		}
		//根据flag 判断是否找到要修改的节点
		if(flag) {
			temp.name = newStuNode.name;
			temp.mark = newStuNode.mark;
		} else { //没有找到
			System.out.printf("没有找到 学号 %d 的节点，不能修改\n", newStuNode.stuNo);
		}
	}
	//删除节点
	//思路
	//1. head 不能动，因此我们需要一个temp辅助节点找到待删除节点的前一个节点
	//2. 说明我们在比较时，是temp.next.stuNo 和  需要删除的节点的stuNo比较
	public void del(int stuNo){
		StuNode temp = head;
		boolean flag = false;
		while (true){
			if (temp.next==null){
				break;
			}
			if(temp.next.stuNo==stuNo){
				flag=true;
				break;
			}
			temp=temp.next;
		}
		if (flag){
			temp.next=temp.next.next;
		}else {
			System.out.printf("要删除的 %d 学号节点不存在",stuNo);
		}

	}







	//显示链表[遍历]
	public void list() {
		//判断链表是否为空
		if(head.next == null) {
			System.out.println("链表为空");
			return;
		}
		//因为头节点，不能动，因此我们需要一个辅助变量来遍历
		StuNode temp = head.next;
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
}
//定义StuNode ， 定义StuNode 对象就是一个节点
class StuNode {
	public int stuNo;
	public String name;
	public String mark;
	public StuNode next; //指向下一个节点
	//构造器
	public StuNode(int stuNo, String name, String mark) {
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
