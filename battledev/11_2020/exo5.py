'''
An interesting property of the rolling hash is that h(s1 + s2) = (h(s1) * k**|s2| + h(s2)) % M. With k = 31 and M = 2**32 in our case.
So if we find a s1 with a hash equals at 0, we have h(s1 + s2) = h(s2), which makes the expected collision :tada:.

The question is, how can we find a string with hash equals at 0 ?
We need h(s1) to be a multiple of M, so it results to 0 after the modulo.
The idea will be to decompose a multiple of M in base k.

Let's decompose 2**32 in base 31 (using [wolframalpha](https://www.wolframalpha.com/examples/mathematics/numbers/base-conversions/)).
The conversion of 2**32 in base 31 is: [4, 26, 0, 19, 29, 24, 4]. Arf, we need integers between 33 and 127.
Given a base b, you can rewrite any number from [i, j] with [i-1, j+b], this is the way we will rewrite this with numbers between 33 and 127.

Unfortunately, we cannot rewrite 2**32 in base 31 correctly using this trick. If we "merge" the 4 and the 26, we have the following:
`[150, 0, 19, 29, 24, 4]`, 150 is out of range.

But we can use another multiple of 2**32. Let's use 8 * 2**32, which gives:
[1, 7, 22, 5, 4, 21, 7, 1] which can be rewritten [37, 52, 35, 34, 51, 37, 32], which is a valid string :tada:.
'''

print('%4#"3$?' + input())
