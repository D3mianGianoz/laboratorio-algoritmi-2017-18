/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

#include <stdlib.h>
#include "ordered_array.h"
#include <stdio.h>


static unsigned long  get_index_to_insert(OrderedArray *ordered_array, void* element);
static void insert_element(OrderedArray *ordered_array, void* element,unsigned long index);



OrderedArray *ordered_array_create(int (*precedes)(void*,void*)){
  OrderedArray *ordered_array = malloc(sizeof(OrderedArray));
  if(ordered_array == NULL){
    fprintf(stderr, "ordered_array_create: unable to allocate memory for the ordered array");
    exit(EXIT_FAILURE);
  }
  
  ordered_array->array = malloc(10*sizeof(void*));
  ordered_array->el_num = 0;
  ordered_array->array_capacity =10;
  ordered_array->precedes = precedes;
  
  return(ordered_array);
}


int ordered_array_is_empty(OrderedArray *ordered_array){
  if(ordered_array == NULL){
    fprintf(stderr,"add_ordered_array_element: ordered_array parameter cannot be NULL");
    exit(EXIT_FAILURE);
  }

  if(ordered_array->el_num == 0)
    return(1);
  return(0);
}


void ordered_array_add(OrderedArray *ordered_array, void* element){
  if(ordered_array == NULL){
    fprintf(stderr,"add_ordered_array_element: ordered_array parameter cannot be NULL");
    exit(EXIT_FAILURE);
  }
  if(element == NULL){
    fprintf(stderr,"add_ordered_array_element: element parameter cannot be NULL");
    exit(EXIT_FAILURE);
  }

  if(ordered_array->el_num >= ordered_array->array_capacity){
    ordered_array->array = realloc(ordered_array->array,2*(ordered_array->array_capacity)*sizeof(void*));
    if(ordered_array->array == NULL){
      fprintf(stderr,"ordered_array_add: unable to reallocate memory to host the new element");
      exit(EXIT_FAILURE);
    }
    ordered_array->array_capacity = 2*ordered_array->array_capacity;
  }

  unsigned long index = get_index_to_insert(ordered_array, element);
  
  insert_element(ordered_array,element,index);
  
  (ordered_array->el_num)++;
  
}


unsigned long ordered_array_size(OrderedArray *ordered_array){
  if(ordered_array == NULL){
    fprintf(stderr,"add_ordered_array_element: ordered_array parameter cannot be NULL");
    exit(EXIT_FAILURE);
  }

  return(ordered_array->el_num);
}


void* ordered_array_get(OrderedArray *ordered_array, unsigned long i){
  if(ordered_array == NULL){
    fprintf(stderr,"add_ordered_array_element: ordered_array parameter cannot be NULL");
    exit(EXIT_FAILURE);
  }  
  if(i>=ordered_array->el_num){
    fprintf(stderr,"ordered_array_get: Index %lu is out of the array bounds",i);
    exit(EXIT_FAILURE);
  }

  return(ordered_array->array)[i];
}


void ordered_array_free_memory(OrderedArray *ordered_array){
  if(ordered_array == NULL){
    fprintf(stderr,"add_ordered_array_element: ordered_array parameter cannot be NULL");
    exit(EXIT_FAILURE);
  }

  free(ordered_array->array);
  free(ordered_array);
}


//returns the position where the new element must be inserted
static unsigned long get_index_to_insert(OrderedArray *ordered_array, void* element){
  unsigned long i = 0;
  int cont = 1;
  while(i<ordered_array->el_num && cont){
    if((*(ordered_array->precedes))(element,(ordered_array->array)[i])){
      cont = 0;
    } else i++;
  }
  
  return(i);
}


//inserts the specified element in the specified ordered array at the specified
//index position
static void insert_element(OrderedArray *ordered_array, void* element,unsigned long index){
  for(unsigned long i = ordered_array->el_num;i>index;i--){
    (ordered_array->array)[i] = (ordered_array->array)[i-1];
  }
  (ordered_array->array)[index] = element;
}
