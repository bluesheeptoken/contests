# BFS

from collections import defaultdict


d = defaultdict(list)
for _ in range(int(input()) - 1):
    a, b = input().split()
    d[b].append(a)

to_visit = ['0']
ans = []

while to_visit:
    ans.append(len(to_visit))
    next_visits = []
    for v in to_visit:
        next_visits += d[v]
    to_visit = next_visits

print(*(ans + (10-len(ans))*[0]))