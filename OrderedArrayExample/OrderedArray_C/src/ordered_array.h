/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   ordered_array.h
 * Author: magro
 *
 * Created on 30 gennaio 2017, 10.10
 */

#ifndef ORDERED_ARRAY_H_laokjsdnbudjllvfidkfmqm
#define ORDERED_ARRAY_H_laokjsdnbudjllvfidkfmqm

//An array of any number of elements of any kind, ordered in non descending order
//according to a specified precedence relation.
typedef struct {
  void** array;
  unsigned long el_num;
  unsigned long array_capacity;
  int (*precedes)(void*,void*);
}OrderedArray;

//It creates an empty ordered array and returns the created ordered array.
//It accepts as input a pointer to a function implementing the 
//relation precedence between the array elements. 
//Such a function must accept as input two pointers to elements and
//return 0 iff the first element does not precede the second one and
//a nuber different from zero otherwise.
OrderedArray *ordered_array_create(int (*precedes)(void*,void*));


//It accepts as input a pointer to an ordered array and it returns 1 iff
//the ordered array is empty (0 otherwise)
int ordered_array_is_empty(OrderedArray *ordered_array);

//It accepts as input a pointer to an ordered array and it
//returns the number of elements currently stored into the array.
unsigned long ordered_array_size(OrderedArray *ordered_array);

//It accepts as input a pointer to an ordered array and a pointer to an element. It adds
//the element to the ordered array in the right position. The pointer to the
//element cannot be NULL.
void ordered_array_add(OrderedArray *ordered_array, void* element);

//It accepts as input a pointer to an ordered array and 
//it frees the memory allocated to store the ordered array. 
//It does not free the memory allocated to store the array elements,
//since freing that memory is responsibility of the function where
//the ordered array was created
void ordered_array_free_memory(OrderedArray *ordered_array);

//It accepts as input a pointer to an ordered array and an integer "i" and
//it returns the pointer to the i-th element of the ordered array
void* ordered_array_get(OrderedArray *ordered_array, unsigned long i);

#endif /* ORDERED_ARRAY_H_laokjsdnbudjllvfidkfmqm */

