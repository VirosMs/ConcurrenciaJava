# Concurrencia en Java

Un hilo (Thread) es la unidad de ejecución más pequeña que puede programar el sistema operativo.

Un proceso es un grupo de hilos asociados que se ejecutan en el mismo entorno compartido (shared environment).

Un proceso single-threaded es aquel proceso que contiene exactamente un hilo.

Un proceso multi-threaded es aquel proceso que admite más de un hilo.

Entorno compartido significa que los hilos en el mismo proceso comparten el mismo espacio de memoria y pueden comunicarse directamente entre sí. Por ejemplo: variables static, variables de instancia y locales pasadas a un hilo.

Una tarea (Task) es una sola unidad de trabajo realizada por un hilo.

Un hilo puede completar varias tareas independientes, pero solo una tarea a la vez.

La propiedad de ejecutar múltiples hilos y procesos al mismo tiempo se conoce como concurrencia.

## ¿Cómo decide el sistema qué ejecutar cuando hay más hilos disponibles que CPU?

Los sistemas operativos utilizan un programador de hilos (Thread scheduler) para determinar qué hilos se deben ejecutar actualmente. Por ejemplo, un thread scheduler puede emplear un programa round-robin en el que cada hilo disponible recibe el mismo número de ciclos de CPU para ejecutarse, con hilos visitados en orden circular. Cuando se completa el tiempo asignado de un hilo, pero el hilo no ha terminado de procesarse, se produce un cambio de contexto.

Un cambio de contexto es el proceso de almacenar el estado actual de un hilo y luego restaurar el estado del hilo para continuar la ejecución.

El cambio de contexto tiene asociado un coste debido a la pérdida de tiempo y la necesidad de recargar el estado de un hilo.

Se debe minimizar la cantidad de cambios de contexto mientras mantienen una aplicación funcionando sin problemas.

Un hilo puede interrumpir o reemplazar a otro hilo si tiene una prioridad de hilo más alta que el otro hilo.

Una prioridad de hilo es un valor numérico asociado con un hilo que el planificador de hilos (Thread scheduler) tiene en cuenta al determinar qué hilos deben ejecutarse actualmente. En Java, las prioridades de hilos se especifican como valores enteros.

## Tipos de hilos

### Hilos de sistema

La máquina virtual de Java (JVM) crea un hilo y se ejecuta en segundo plano de la aplicación. Por ejemplo, el recolector de basura (garbage collector) es administrado por un hilo del sistema creado por la JVM. Por defecto son hilos daemon, es decir, que si no hay nada más pendiente de ejecución entonces el programa se cierra una vez se terminen.

### Hilos definidos por el usuario

Es uno creado por el desarrollador de la aplicación para realizar una tarea específica. No son hilos daemon, por tanto el programa espera a que terminen su trabajo. Se puede convertir en hilos daemon con el método `.setDaemon(true)`.

## Ciclo de vida de los hilos

Un hilo puede pasar por varios estados durante su ciclo de vida. El método `getState()` de Thread devuelve una constante de enumeración que indica el estado actual del hilo, que cae en uno de los siguientes valores:

- NEW
- RUNNABLE
- BLOCKED
- WAITING
- TIMED_WAITING
- TERMINATED

Estas constantes de enumeración se definen en la enumeración `Thread.State` y significan lo siguiente:

- NEW: cuando se crea un hilo pero no se ha ejecutado (no se ha invocado el método `start()`), está en este estado.
- RUNNABLE: cuando se ha invocado el método `start()`, el hilo entra en el estado ejecutable y su método `run()` se está ejecutando. Se debe tener en cuenta que el hilo puede volver al estado ejecutable desde otro estado (en espera, bloqueado), pero es posible que el scheduler de hilos no lo seleccione de inmediato, de ahí el término "ejecutable", no en ejecución.
- BLOCKED: cuando un hilo intenta adquirir un bloqueo intrínseco (no un bloqueo en el paquete `java.util.concurrent`) que actualmente está en manos de otro hilo, se bloquea. Cuando todos los demás hilos han renunciado al bloqueo y el planificador de hilos ha permitido que este hilo mantenga el bloqueo, el hilo se desbloquea y entra en el estado ejecutable.
- WAITING: un hilo entra en este estado si espera a que otro hilo le notifique, lo cual es el resultado de llamar a `Object.wait()` o `Thread.join()`. El hilo también entra en estado de espera si espera un Bloqueo o una Condición en el paquete `java.util.concurrent`. Cuando otro hilo llama al método `notify()/notifyAll()` de `Object` o a `signal()/signalAll()` de `Condition`, el hilo vuelve al estado ejecutable.
- TIMED_WAITING: un hilo entra en este estado si se llama a un método con parámetro de tiempo de espera: `sleep()`, `wait()`, `join()`, `Lock.tryLock()` y `Condition.await()`. El hilo sale de este estado si se agota el tiempo de espera o si se ha recibido la notificación adecuada.
- TERMINATED: un hilo entra en estado terminado cuando ha completado la ejecución. El subproceso termina por una de dos razones:
  - el método `run()` sale termina exitosamente.
  - el método `run()` sale termina de forma abrupta debido a que ocurre una excepción no detectada.

## Polling

Aunque la programación de multihilo permite ejecutar varias tareas al mismo tiempo, es común que un hilo necesite esperar a obtener los resultados de otro hilo. Una solución es utilizar el polling, es el proceso de verificar datos de forma intermitente en un intervalo fijo.

### Métodos para pausar, parar, esperar:

- `sleep()`
- `interrupt()`
- `isAlive()`
- `getState()`
- `join()`

## API Concurrency

`java.util.concurrent`

Al escribir programas de hilos múltiples en la práctica, es mejor usar la API de concurrencia en lugar de trabajar con objetos de hilos directamente. La interfaz `Callable` a menudo es preferible a `Runnable`, ya que permite recuperar fácilmente más detalles de la tarea una vez que se completa.

## Pool de hilos

Un pool de hilos es un grupo de hilos reutilizables instanciados previamente que están disponibles para realizar un conjunto de tareas arbitrarias.

Se puede crear un executor de un solo hilo:

```java
ExecutorService executor = Executors.newSingleThreadExecutor();

