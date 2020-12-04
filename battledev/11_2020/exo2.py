n = int(input())
s = 0
for _ in range(n):
    h, m = map(int, input().split(':'))
    s += h < 8 or h >= 20
if s > n // 2:
    print("SUSPICIOUS")
else:
    print("OK")