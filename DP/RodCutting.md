## Problem Statement - 
Given a rod of length n inches and an array of prices that contains prices of all pieces of size smaller than n. Determine the locations where the cuts are to be made for maximum profit.
I need help how can we approach this problem this was asked during microsoft interview ?

## Solution In Java using Iterative DP ->

# Count of rod pieces of the particular length required to build given ROD

    public static int[] rodMaxPrice(int n, int[] price){
        int[] maxPriceArr = new int[price.length];
        int[][] rodCountArr = new int[price.length][price.length];
        for (int maxPriceId = 0; maxPriceId < price.length; maxPriceId++) {
            int maxPrice = price[maxPriceId], currLen=maxPriceId+1, maxPriceRodLen=currLen;
            for (int rodLen = 1; rodLen <= currLen/2; rodLen++) {
                int currPrice = maxPriceArr[rodLen-1] + maxPriceArr[currLen-rodLen-1];
                if(maxPrice < currPrice){
                    maxPrice = currPrice;
                    maxPriceRodLen = rodLen;
                }
            }
            maxPriceArr[maxPriceId] = maxPrice;
            updateArr(rodCountArr,maxPriceRodLen,currLen);
            updateArr(rodCountArr,currLen-maxPriceRodLen,currLen);
        }

        return rodCountArr[n-1];
    }
    public static void updateArr(int[][] rodCountArr,int len,int currLen){
        if(len > rodCountArr.length || len < 1 || len > currLen) return;
        else if(len == currLen){
            rodCountArr[currLen-1][currLen-1] = 1;
            return;
        }
        for(int id=0;len>id;id++){
            rodCountArr[currLen-1][id] += rodCountArr[len-1][id];
        }
    }