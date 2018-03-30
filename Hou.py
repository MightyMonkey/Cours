# -*- coding: utf-8 -*-
"""
Spyder Editor

This is a temporary script file.
"""
import Ciw
import matplotlib.pyplot as plt

def time_dependent_function(t):
    if t % 24 < 8.0: return 0.1
    if t % 24 > 20.0: return 0.1
    return 0.0833


N = ciw.create_network(
        Arrival_distributions={'Class 1':[['TimeDependent',time_dependent_function],'NoArrivals','NoArrivals'],
                               'Class 2':[['TimeDependent',time_dependent_function],'NoArrivals','NoArrivals'],
                               'Class 3':[['TimeDependent',time_dependent_function],'NoArrivals','NoArrivals'],
                               'Class 4':[['TimeDependent',time_dependent_function],'NoArrivals','NoArrivals'],
                               'Class 5':[['TimeDependent',time_dependent_function],'NoArrivals','NoArrivals'],},
        Service_distributions=[['Exponential',8.0]],
        Transition_matrices=[[0.0]],
        Priority_classes={'Class 1': 0,'Class 2': 1,'Class 3': 2,'Class 4': 3,'Class 5': 4},
        Number_of_servers=[[[4, 8], [6, 20], [4, 24]]])
average_waits = []
warmup=10
cooldown=10
maxsimtime=40
