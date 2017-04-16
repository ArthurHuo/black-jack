package com.tnzyq;

import java.util.Random;

/**
 * 荷官，发牌人类
 * @author 霍佳彤
 *
 */
public class Dealer {

	int maxSize = 52;//最大牌数
	Card[] cards;//能够发派的扑克
	Random random;//用于随机生成数字的random
	
	/**
	 * 构造方法
	 */
	public Dealer() {
		//生成52张牌
		cards = new Card[maxSize];
		random = new Random();
		shuffle();
	}
	
	/**
	 * 返回这副牌剩余的张数
	 * @return
	 */
	public int getSize() {
		int count = 0;
		
		for (int i = 0; i < maxSize; i++) {
			if(cards[i] != null){
				count++;
			}
		}
		
		return count;
	}
	
	/**
	 * 洗牌
	 */
	public void shuffle() {
		for(int i = 0; i < maxSize; i++){
			cards[i] = new Card(i);
		}
	}
	
	/**
	 * 随机发牌
	 * @return
	 */
	public Card getCard() {
		if (getSize() == 0) { //如果牌没了 洗牌
			shuffle();
		}
		Card card = null;
		while (true) {
			int index = random.nextInt(maxSize);//以52张牌为随机种子，生成0-52的随机数
			while(cards[index] == null){
				index = random.nextInt(maxSize);
			}
			
			card = cards[index];//取出还没有发出的牌
			cards[index] = null;//被取出的牌置空
			break;
		}
		return card;
	}
	
}
