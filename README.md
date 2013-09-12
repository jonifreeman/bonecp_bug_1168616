# Usage

1. Create a test database

# Give password 'secret' or change the given password in benchmark.scala
sudo -u postgres createuser -P bonecptest 
sudo -u postgres createdb -O bonecptest bonecptest
sudo -u postgres psql bonecptest < src/main/resources/schema.sql

2. Start REPL

./sbt console

3. Run the benchmark

scala> benchmark.Test.run

# Results on Joni's machine

## bonecp 0.7.1

    scala> benchmark.Test.run
    3208ms

    scala> benchmark.Test.run
    3641ms

    scala> benchmark.Test.run
    3303ms

    scala> benchmark.Test.run
    3153ms

    scala> benchmark.Test.run
    2781ms

## bonecp 0.8.0-alpha1 (edit build.scala and reboot sbt)

    scala> benchmark.Test.run
    6495ms

    scala> benchmark.Test.run
    5624ms

    scala> benchmark.Test.run
    5017ms

    scala> benchmark.Test.run
    5356ms

    scala> benchmark.Test.run
    5037ms

Note, disabling statement cache in 0.7.1 version will give similar results as with 0.8.0-alpha1. A strong indicator that it is statement caching which causes the performance degradation.

