# implementation of SNS server

## Timeline Implementation
#### Follow Table
| id  | from | to  |
|-----|------|-----|
| 1   | 1    | 3   |
| 2   | 1    | 2   |
| 3   | 3    | 1   |
| ... | ...  | ... |
#### Post Table
| id  | memberId | 
|-----|----------|
| 1   | 3        |
| 2   | 1        |
| 3   | 2        |
| ... | ...      | 

When we have the above tables,   
we need `log(# Follow's records) + {(# Following of a member)*log(# Post's records)}` time complexity 
to make a timeline of a particular member.  
Therefore, when implementing a timeline in general,  
the more followings a user has,  
the more time to create the timeline for the user it takes.
