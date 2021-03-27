# PreventBotAttack
Bot による攻撃をブロックしたり、負荷を低減させたりするプラグイン。

## 設定

```yaml
limit:
  cps: 10 # Connection Per Second
debug:
  disconnect: false # console log: "[/0.0.0.0:00000] <-> Disconnected by ConnectionLimiter"
command: # Run System Command
  blacklist:
    add: "" # On Add BlackList. variable: (%ip% -> 0.0.0.0)
    remove: "" # On Remove BlackList. variable: (%ip% -> 0.0.0.0)
  timeout: 100 # Command TimeOut: e.g. 100 ms
```

### limit.cps

１秒当たりの許容接続数。これを超えた場合、ブラックリストに自動で追加する。

### debug.disconnect

PlayerHandShakeEvent で切断した時にコンソールログを送信するか設定する。

### command.blacklist.add

ブラックリストに追加する時に実行するシステムコマンドを設定する。実行時に `%ip%` を接続者の IP に置き換える。

```
例: ipset -! -A blacklist %ip%
```

### command.blacklist.remove

ブラックリストから削除する時に実行するシステムコマンドを設定する。実行時に `%ip%` を接続者の IP に置き換える。

```
例: ipset d blacklist %ip%
```

### command.timeout

システムコマンドのタイムアウト時間を設定する。

## コマンド

### /blacklist

| コマンド | 説明 |
|--------|------|
| /blacklist add <IP> | 手動で IP をブラックリストに追加する |
| /blacklist remove <IP> | 手動で IP をブラックリストから削除する |
| /blacklist list | ブラックリストの一覧を表示する |