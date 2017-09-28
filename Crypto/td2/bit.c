/****************************************************************
*                                                               *
*  -------------------------- bit.c --------------------------  *
*                                                               *
****************************************************************/

#include <string.h>

#include "bit.h"

/****************************************************************
*                                                               *
*  ------------------------- bit_get -------------------------  *
*                                                               *
****************************************************************/

int bit_get(const unsigned char *bits, int pos) {

unsigned char      masque;

int                i;

/****************************************************************
*                                                               *
*  Configure un masque pour le bit � r�cup�rer.                 *
*                                                               *
****************************************************************/

masque = 0x80;

//boucle optimisable
for (i = 0; i < (pos % 8); i++)
   masque = masque >> 1;
//les pos%/8 servent � naviguer dans les cases du tableau:
//pos%8 sert � obtenir la position dans la case et pos/8 � obtenir la case

/****************************************************************
*                                                               *
*  Renvoie le bit.                                              *
*                                                               *
****************************************************************/

//AND entre masque et bits -> il ne reste que le bit au niveau que l'on veut: on peut comparer ce bit avec le masque. Si egaux alors on renvoie 1
return (((masque & bits[(int)(pos / 8)]) == masque) ? 1 : 0);

}

/****************************************************************
*                                                               *
*  -------------------------- bit_set ------------------------  *
*                                                               *
****************************************************************/

void bit_set(unsigned char *bits, int pos, int etat) {

unsigned char      masque;

int                i;

/****************************************************************
*                                                               *
*  Configure un masque pour le bit � positionner.               *
*                                                               *
****************************************************************/

masque = 0x80;

//on remet la position en se servant de pos%8
for (i = 0; i < (pos % 8); i++)
   masque = masque >> 1;

/****************************************************************
*                                                               *
*  Positionne le bit.                                           *
*                                                               *
****************************************************************/

if (etat)//si on veut mettre � 1 on fait ou pour que �a donne forc�ment 1 � l'endroit voulu
   bits[pos / 8] = bits[pos / 8] | masque;
else//si on veut mettre � 0 on fait ET de l'inverse du masque pour que ca donne forc�ment 0 � l'endroit voulu
   bits[pos / 8] = bits[pos / 8] & (~masque);

return;

}

/****************************************************************
*                                                               *
*  ------------------------- bit_xor -------------------------  *
*                                                               *
****************************************************************/

void bit_xor(const unsigned char *bits1,
             const unsigned char *bits2, 
             unsigned char *bitsx, int taille) {

int                i;

/****************************************************************
*                                                               *
*  Effectue le XOR (OU exclusif) bit � bit des deux tampons.    *
*                                                               *
****************************************************************/

for (i = 0; i < taille; i++) {

   if (bit_get(bits1, i) != bit_get(bits2, i))
      bit_set(bitsx, i, 1);
   else
      bit_set(bitsx, i, 0);

}

return;

}

/****************************************************************
*                                                               *
*  ---------------------- bit_rot_left -----------------------  *
*                                                               *
****************************************************************/

void bit_rot_left(unsigned char *bits, int taille, int nbre) {

int                fbit,
                   lbit,
                   i,
                   j;

/****************************************************************
*  Rotation du tampon du nbre de fois sp�cifi� vers la gauche.  *
****************************************************************/

if (taille > 0) {

   for (j = 0; j < nbre; j++) {

      for (i = 0; i <= ((taille - 1) / 8); i++) {

         /*******************************************************
         *  R�cup�re le bit � sortir de l'octet courant.        *
         *******************************************************/
	//on prend le premier bit du mot
         lbit = bit_get(&bits[i], 0);

         if (i == 0) {

            /****************************************************
            *  Sauve le bit sorti du premier octet.             *
            ****************************************************/

            fbit = lbit;

            }

         else {

            /****************************************************
            *  Positionne le bit le plus � droite de l'octet    *
            *  pr�c�dent avec le bit le plus � gauche,          *
            *  qui sortira de l'octet courant.                  *
            ****************************************************/

            bit_set(&bits[i - 1], 7, lbit);

         }

         /*******************************************************
         *  D�cale l'octet courant vers la gauche.              *
         *******************************************************/

         bits[i] = bits[i] << 1;

      }

      /**********************************************************
      *  Positionne le bit le plus � droite du tampon           *
      *   avec le bit sorti du premier octet.                   *
      **********************************************************/

      bit_set(bits, taille - 1, fbit);

   }

}

return;

}
