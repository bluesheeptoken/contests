import sys, itertools, functools, collections, math, hashlib, random, datetime

################################### Enigme 1 ###################################
def enigme1():
    return sum(
        x**2 + y**2 < 5000**2 for x in range(-6000, 6000, 10) for y in range(-6000, 6000, 10)
    )

################################### Enigme 2 ###################################
def enigme2():
    ha = "ccee0f6b78bba5eff19a89678252a0fcece57d3b80458ceeb41c92bfdfa645ce"
    for m in [3, 2]:
        for d in range(1, 32):
            for x in range(-5000, 5001, 10):
                for y in range(-5000, 5001, 10):
                    if x and y % x == 0:
                        message = f"{d}/{m}@{x},{y}"
                        if hashlib.sha256(message.encode()).hexdigest() == ha:
                            return message

################################### Enigme 3 ###################################
def enigme3():
    res = ''
    with open("medic.txt") as f:
        *l, = map(lambda x: x.strip(), f.readlines())
    for i in range(12):
        res += collections.Counter(m[i] for m in l if i < len(m)).most_common()[0][0]
    return res

################################### Enigme 4 ###################################
def enigme4():
    def step(state):
        pat = {
           "111": '0',
           "110": '1',
           "101": '1',
           "100": '0',
           "011": '1',
           "010": '1',
           "001": '1',
           "000": '0'
        }

        return ''.join(pat[(state*3)[49+i:49+i+3]] for i in range(50))

    state = "10000101010000100100001001111001011010100101101100"
    while True:
        if '0' * 15 not in state:
            return state
        state = step(state)

################################### Enigme 5 ###################################
def enigme5():

    @functools.lru_cache(maxsize=None)
    def loop(x, y):
        if x <= 0:
            return 0
        if y <= 0:
            return 1
        return loop(x-2, y) + loop(x-5, y) + loop(x-7, y) + loop(x, y-2) + loop(x, y-5) + loop(x, y-7)

    return loop(100, 100) % 10**9

###################################   main   ###################################
def main(i=None):
    for solution in map(lambda i: globals()[f"enigme{i}"](), range(1,6) if i is None else [i]):
        print(solution)

if __name__ == "__main__":
    main(*map(int, sys.argv[1:]))
