package nix.util;

public class FindTheCheapestWay {
    private static boolean continueCount(int[][] weight, int start, int end){
        for(int i=start; i<end+1; i++){
            for(int j=start; j<end+1; j++){
                if(weight[i][j]<0)
                    return true;
            }
        }
        return false;
    }
    private static void print(int[][] weight){
        for(int i=0; i<weight.length; i++){
            for(int j=0; j<weight.length; j++){
                System.out.print(weight[i][j]+"    ");
            }
            System.out.println();
        }
    }

    public static int task(int start, int end,  int[][]values){
        int maxValue = 200_000;
        int pastKeyValue = 0;
        int [][] weight = new int[values.length][values.length];
        for(int i=0; i<end+1; i++){
            for(int j=0; j<end+1; j++){
                weight[i][j]= -1;
                if(i>=start && j==start) weight[i][j] = 0;
                if(i==start && j>start) weight[i][j] = maxValue;
            }
        }
        int countI = start+1;
        int countJ = start+1;
        pastKeyValue = weight[start][start];
        do{
            int min=0; int minI=0; int minJ=0;
            for(int j=countJ; j<end+1; j++){
                if(weight[countI][j]<=0 ){
                    if(values[countI-1][j]==0) weight[countI][j]=maxValue;
                    else weight[countI][j] = values[countI-1][j]+pastKeyValue;
                    if(j==countJ || min>weight[countI][j]) {
                        min=weight[countI][j];
                        minI = countI; minJ = j;
                    }
                }
            }
            pastKeyValue = min;
            for(int a=minI+1; a<end+1; a++){
                weight[a][minJ] = min;
            }
            countI++;
            countJ++;
        }
        while(continueCount(weight, start, end));

        return weight[end][end];

    }
}
