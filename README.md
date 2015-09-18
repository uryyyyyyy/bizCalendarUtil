
# usage of quartz-scheduler

## 概要

JobDetailとTriggerによって定期実行を行う。


## JobDetail

JobBuilderによって作られる。

newJob -> どのJobクラスを実行するか選ぶ（匿名クラスでは動かなかった。）
storeDurably -> Triggerに参照されなくても存在し続けるか否か
requestRecovery -> 失敗時に再実行するか否か
setJobData -> Job実行時に使える変数群をsetする。
withIdentity -> 名前を付ける。
