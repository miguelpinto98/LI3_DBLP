typedef  unsigned long  int  ub4;
typedef  unsigned       char ub1;

#define hashsize(n) ((ub4)1<<(n))
#define hashmask(n) (hashsize(n)-1)

ub4 hash(register ub1 *k,register ub4  length, register ub4  initval);