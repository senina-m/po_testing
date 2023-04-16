import pandas as pd
from datetime import datetime
import csv
import matplotlib.pyplot as plt
import matplotlib.dates as mdates
import sys
print(sys.argv[1])
df = pd.read_csv(sys.argv[1], delimiter=',')

print (df)

plt.plot(df["x"], df["res"])
plt.gcf().autofmt_xdate()
plt.show()