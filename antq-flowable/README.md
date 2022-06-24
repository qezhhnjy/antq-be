- ProcessDefinition

这个最好理解，就是流程的定义，也就相当于规范，每个 ProcessDefinition 都会有一个 id。

- ProcessInstance

这个就是流程的一个实例。简单来说，ProcessDefinition 相当于是类，而 ProcessInstance 则相当于是根据类 new 出来的对象。

- Activity

Activity 是流程标准规范 BPMN2.0 里面的规范，流程中的每一个步骤都是一个 Activity。

- Execution

Execution 的含义是流程的执行线路，通过 Execution 可以获得当前 ProcessInstance 当前执行到哪个 Activity了。

- Task

Task 就是当前要做的工作。