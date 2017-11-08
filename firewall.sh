iptables -t filter -F
iptables -t nat -F
iptables -t nat -A POSTROUTING -o eth0 -j MASQUERADE
iptables -t nat -A PREROUTING -p tcp --dport 22 -j DNAT --to-destination 172.20.0.1

