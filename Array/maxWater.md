# Container With Most Water

## Two pointers Approach - 


1. **Intuition behind the 2 pointers approach** is that, for water we need a container and here its formed **using 2 poles**. So to find 2 poles with **max water**, poles should be placed at the **farthest possible position**, which here will be 0 and n-1 index.

2. We have **2 poles** one at 0 & second at n-1 index **(farthest possible position)**, we can calculate **current water** with these poles.

	a. Now **skip pole or poles** which are of **lesser height** between them bcoz – 

      1. Here only we **get max water** possible using this **lesser height pole** bcoz we **utilized its full height** here. Finally water height can not be increased more using this pole with an unvisited pole.
      2. So **no need to calculate water again using visited & this lesser height pole** bcoz we already calculated max water associated with those visited poles. 

    b. **Keep a pole** of **higher height** out of these two , bcoz it still can make more water with further unvisited poles bcoz we **have not** yet **utilized** the **complete height of the pole**.

**Complexity Analysis** - 
* **Time Complexity** T(n) - O(n)
* **Space Complexity** S(n) - O(1) 
where ‘n’ is the size of the nums array.

**CODE**

```js
 var maxArea = function(height) {
  let maxWater = 0, leftId=0, rightId=height.length-1;
  while(leftId<rightId){
    let leftPole = height[leftId], rightPole = height[rightId];
    let currWater = Math.min(leftPole, rightPole)*(rightId-leftId);
    maxWater = Math.max(currWater, maxWater);
    // skip pole/poles of min hieght
    if(leftPole<=rightPole) leftId++;
    if(rightPole<=leftPole) rightId--;
  }
  return maxWater;  
};
```
