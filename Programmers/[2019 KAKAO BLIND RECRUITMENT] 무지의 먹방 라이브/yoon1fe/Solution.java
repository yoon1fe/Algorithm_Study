package Programmers;

import java.util.*;

public class Solution {
	static class Node implements Comparable<Node> {
		long idx, time;

		Node(long idx, long time) {
			this.idx = idx;
			this.time = time;
		}

		public int compareTo(Node n) {
			return (int) (this.time - n.time);
		}
	}

	public static int solution(int[] food_times, long k) {
		long answer = 0;
		Node[] food = new Node[food_times.length];

		for (int i = 0; i < food_times.length; i++) {
			food[i] = new Node(i + 1, food_times[i]);
		}
		Arrays.sort(food);

		long sum = 0;
		long remainder = 0;
		int size = food.length;
		int idx = 0; // 처리된 음식순서 다음번 index
		boolean flag = false; // k가 food_times를 넘어갈경우

		for (int i = 0; i < size; i++) {
			long perv = sum;

			if (i == 0) {
				sum += food[i].time * size;
			} else {
				sum += (food[i].time - food[i - 1].time) * (size - i);
			}

			if (sum > k) {
				remainder = k - perv;
				idx = i;
				flag = true;
				break;
			}
			food[i].idx = -1; // 처리된 음식
		}

		if (!flag)
			return -1;

		Arrays.sort(food, new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return (int) (o1.idx - o2.idx);
			}
		});

		int len = size - idx;
		int count = 0;
		int ans = 0;
		answer = remainder % (long) len;

		for (int i = 0; i < size; i++) {
			if (food[i].idx == -1)
				continue;
			if (count == answer) {
				ans = i;
				break;
			}
			count++;
		}
		return (int) food[ans].idx;
	}

	public static void main(String[] args) {
//		int[] f = {3, 1, 2};
		int[] f = { 946, 314, 757, 322, 559, 647, 983, 482, 145 };
//		long k = 5;
		long k = 1833;
		System.out.println(solution(f, k));
	}
}