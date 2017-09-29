#include <stdio.h>
#include <stdlib.h>
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

int gcd ( Huge a, Huge b ){
  Huge c;
  while( a != 0 ) {
     c = a; a = b%a;  b = c;
  }
  return b;
}

static Huge rsa_crypt(Huge e,Huge n,Huge m){
	if(1<m&&m<n){
	return modexp(m,e,n);
	}
	printf("echec chiffrement: mot trop long\n");
	return 0;	
}

static Huge rsa_decrypt(Huge d,Huge c,Huge n){
	return modexp(c,d,n);
}

int main(int argc, char* argv[]){
	Huge p=atol(argv[1]);
	Huge q=atol(argv[2]);
	Huge n=p*q;
	Huge phi=(p-1)*(q-1);
	//e
	Huge e=2;
	while(gcd(e,phi)!=1){
		e++;
	}
	//d
	Huge d=phi;
	while(((e*d)%phi)!=1){
		d--;
	}
	printf("clef publique (%li,%li)\n clef privee %li\n",e,n,d);
	Huge f=rsa_crypt(e,n,3333);
	printf("mot crypte %li\n",f);
	f=rsa_decrypt(d,f,n);
	printf("mot decrypte %li\n",f);
	return 1;
}
