#ifndef ORDERED_ARRAY_H_costi
#define ORDERED_ARRAY_H_costi

// An Array of any number of elements of any kind, ordered in non-descending order according
// to a specified precedence relation
typedef struct {
    void** array;
    unsigned long el_num;
    unsigned long array_capacity;
    int (*precedes)(void*,void*);
} OrderedArray;

// It create an empty array and returns (a pointer to)  the created ordered array
// It accepts as input a function that inplements the vay that 2 elements of the array are compared
OrderedArray* ordered_array_create(int (*precedes)(void*,void*));

// It disallocate the memory of the ordered_array previously allocated
void ordered_array_free_memory(OrderedArray* ordered_array);

// It checks if the ordere_array is empty or not
int ordered_array_is_empty(OrderedArray* ordered_array);

// It return the size of the ordered_array
unsigned long ordered_array_size(OrderedArray* ordered_array);

// It add the void* item to the ordered_array
void ordered_array_add(OrderedArray* ordered_array,void* element);

// It return the element of the ordered_array in the given i position
void* ordered_array_get(OrderedArray* ordered_array,unsinged long i);



#endif