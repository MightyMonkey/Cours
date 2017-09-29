#include <stdio.h>
typedef unsigned long int Huge;

static Huge modexp(Huge a, Huge b, Huge n){
	Huge y;
	y=1;
	while(b!=0){
		if(b&1)
			y=(y*a)%n;
		a=(a*a)%n;
		b=b>>1;
	}
return y;
}

void main(int argc,char argv[]){

printf("%i",modexp(argv[1],argv[2],argv[3]));
}
