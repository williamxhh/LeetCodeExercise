#include<iostream>
#include<vector>
#include<string>
using namespace std;

class Solution {
public:
	string minWindow(string S, string T) {
		if(S.size() == 0 || T.size() == 0 || T.size() > S.size())
		{
			return "";
		}

		vector<int> needInT(256,0);
		vector<int> containInS(256,0);

		for(int i = 0; i < T.size(); i++)
			needInT[T[i]]++;

		int validCount = 0;
		int containEnd;

		for(containEnd = 0;containEnd < S.size();containEnd++)
		{
			if(containInS[S[containEnd]] < needInT[S[containEnd]])
			{
				validCount++;
			}
			containInS[S[containEnd]]++;
			if(validCount == T.size()) break;
		}
		if(containEnd == S.size())
			return "";

		int minStartIndex = 0;

		while(minStartIndex < S.size() && containInS[S[minStartIndex]] > needInT[S[minStartIndex]])
		{
			containInS[S[minStartIndex]]--;
			minStartIndex++;
		}

		int currentStart = minStartIndex;
		int minEnd = containEnd;

		for(int i = minEnd+1;i < S.size();i++)
		{
			containInS[S[i]]++;
			int newStart = currentStart;
			while(containInS[S[newStart]] > needInT[S[newStart]])
			{
				containInS[S[newStart]]--;
				newStart++;
			}
			currentStart = newStart;
			if((i-newStart) < (minEnd - minStartIndex))
			{
				minEnd = i;
				minStartIndex = newStart;
			}

		}
		return S.substr(minStartIndex,minEnd - minStartIndex + 1);
	}
};

int main()
{
	string s = "ADOBECODEBANC";
	string t = "ABC";
	Solution so;
	cout<<so.minWindow(s,t)<<endl;
}