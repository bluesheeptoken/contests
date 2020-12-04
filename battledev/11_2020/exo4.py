n, m = map(int, input().split())
*l, = map(int, input().split())

# Precompute cumulated xor
tmp = 0
precomputed = []
for i in l:
    tmp ^= i
    precomputed.append(tmp)

ans = [0] * 256
for _ in range(m):
    L, R = map(int, input().split())
    if L == 0:
        ans[precomputed[R]] += 1
    else:
        ans[precomputed[L-1]^precomputed[R]] += 1
print(*ans)
