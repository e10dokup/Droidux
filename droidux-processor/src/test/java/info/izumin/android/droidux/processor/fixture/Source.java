package info.izumin.android.droidux.processor.fixture;

/**
 * Created by izumin on 11/2/15.
 */
public final class Source {
    public static final String TAG = Source.class.getSimpleName();

    public static final String[] EMPTY = {};

    public static final class StoreImpl {
        public static final String[] COUNTER = {
                "package info.izumin.android.droidux.sample;",
                "",
                "import info.izumin.android.droidux.Action;",
                "import info.izumin.android.droidux.StoreImpl;",
                "import info.izumin.android.droidux.processor.fixture.Counter;",
                "import info.izumin.android.droidux.processor.fixture.CounterReducer;",
                "import info.izumin.android.droidux.processor.fixture.action.ClearCountAction;",
                "import info.izumin.android.droidux.processor.fixture.action.IncrementCountAction;",
                "import info.izumin.android.droidux.processor.fixture.action.InitializeCountAction;",
                "import info.izumin.android.droidux.processor.fixture.action.SquareCountAction;",
                "",
                "final class DroiduxRootStore_CounterStoreImpl extends StoreImpl<Counter> {",
                "    private final CounterReducer reducer;",
                "",
                "    protected DroiduxRootStore_CounterStoreImpl(Counter state, CounterReducer reducer) {",
                "        super(state);",
                "        this.reducer = reducer;",
                "    }",
                "",
                "    @Override",
                "    protected void dispatch(Action action) {",
                "        Class<? extends Action> actionClass = action.getClass();",
                "        Counter result = null;",
                "        if (IncrementCountAction.class.isAssignableFrom(actionClass)) {",
                "            result = reducer.increment(getState(), (IncrementCountAction) action);",
                "        }",
                "        if (SquareCountAction.class.isAssignableFrom(actionClass)) {",
                "            result = reducer.square(getState());",
                "        }",
                "        if (InitializeCountAction.class.isAssignableFrom(actionClass)) {",
                "            result = reducer.initialize((InitializeCountAction) action);",
                "        }",
                "        if (ClearCountAction.class.isAssignableFrom(actionClass)) {",
                "            result = reducer.clear();",
                "        }",
                "        if (result != null) {",
                "            setState(result);",
                "        }",
                "    }",
                "}"
        };

        public static final String[] TODO_LIST = {
                "package info.izumin.android.droidux.sample;",
                "",
                "import info.izumin.android.droidux.Action;",
                "import info.izumin.android.droidux.UndoableStoreImpl;",
                "import info.izumin.android.droidux.processor.fixture.TodoList;",
                "import info.izumin.android.droidux.processor.fixture.TodoListReducer;",
                "import info.izumin.android.droidux.processor.fixture.action.AddTodoItemAction;",
                "",
                "final class DroiduxRootStore_TodoListStoreImpl extends UndoableStoreImpl<TodoList> {",
                "    private final TodoListReducer reducer;",
                "",
                "    protected DroiduxCounterStore_CounterStoreImpl(TodoList state, TodoListReducer reducer) {",
                "        super(state);",
                "        this.reducer = reducer;",
                "    }",
                "",
                "    @Override",
                "    protected void dispatch(Action action) {",
                "        super.dispatch(action);",
                "        Class<? extends Action> actionClass = action.getClass();",
                "        TodoList result = null;",
                "        if (AddTodoItemAction.class.isAssignableFrom(actionClass)) {",
                "            result = reducer.add(getState().clone(), (AddTodoItemAction) action);",
                "        }",
                "        if (result != null) {",
                "            setState(result);",
                "        }",
                "    }",
                "}"
        };
    }

    public static class Counter {
        public static final String[] TARGET = {
                "package info.izumin.android.droidux.sample;",
                "import info.izumin.android.droidux.annotation.Store;",
                "import info.izumin.android.droidux.Action;",
                "import info.izumin.android.droidux.processor.fixture.Counter;",
                "import info.izumin.android.droidux.processor.fixture.CounterReducer;",
                "import rx.Observable;",
                "@Store({CounterReducer.class})",
                "public interface RootStore {",
                "    Counter counter();",
                "    Observable<Counter> observeCounter();",
                "    Observable<Action> dispatch(Action action);",
                "}"
        };

        public static final String[] GENERATED_STORE = {
                "package info.izumin.android.droidux.sample;",
                "",
                "import info.izumin.android.droidux.Action;",
                "import info.izumin.android.droidux.Dispatcher;",
                "import info.izumin.android.droidux.Middleware;",
                "import info.izumin.android.droidux.exception.NotInitializedException;",
                "import info.izumin.android.droidux.processor.fixture.Counter;",
                "import info.izumin.android.droidux.processor.fixture.CounterReducer;",
                "import java.util.ArrayList;",
                "import java.util.List;",
                "import rx.Observable;",
                "",
                "public final class DroiduxRootStore implements RootStore {",
                "    private final DroiduxRootStore_CounterStoreImpl counterStoreImpl;",
                "    private final Dispatcher dispatcher;",
                "",
                "    protected DroiduxRootStore(Builder builder) {",
                "        counterStoreImpl= new DroiduxRootStore_CounterStoreImpl(builder.counter, builder.counterReducer);",
                "        dispatcher = new Dispatcher(builder.middlewares, counterStoreImpl);",
                "    }",
                "",
                "    public Counter counter() {",
                "        return counterStoreImpl.getState();",
                "    }",
                "",
                "    public Observable<Counter> observeCounter() {",
                "        return counterStoreImpl.observe();",
                "    }",
                "",
                "    public Observable<Action> dispatch(Action action) {",
                "        return dispatcher.dispatch(action)",
                "    }",
                "",
                "    public static final class Builder {",
                "        private final List<Middleware> middlewares;",
                "        private CounterReducer counterReducer;",
                "        private Counter counter;",
                "",
                "        public Builder() {",
                "            middlewares = new ArrayList<>();",
                "        }",
                "",
                "        public Builder addMiddleware(Middleware middleware) {",
                "            middlewares.add(middleware);",
                "            return this;",
                "        }",
                "",
                "        public Builder setReducer(CounterReducer counterReducer) {",
                "            this.counterReducer = counterReducer;",
                "            return this;",
                "        }",
                "",
                "        public Builder setInitialState(Counter counter) {",
                "            this.counter = counter;",
                "            return this;",
                "        }",
                "",
                "        public DroiduxRootStore build() {",
                "            if (counterReducer == null) {",
                "                throw new NotInitializedException(\"CounterReducer has not been initialized.\");",
                "            }",
                "            if (counter == null) {",
                "                throw new NotInitializedException(\"Counter has not been initialized.\");",
                "            }",
                "            return new DroiduxRootStore(this);",
                "        }",
                "    }",
                "}"
        };
    }

    public static class CombinedTwoReducers {
        public static final String[] TARGET = {
                "package info.izumin.android.droidux.sample;",
                "import info.izumin.android.droidux.annotation.Store;",
                "import info.izumin.android.droidux.Action;",
                "import info.izumin.android.droidux.processor.fixture.CounterReducer;",
                "import info.izumin.android.droidux.processor.fixture.TodoListReducer;",
                "import info.izumin.android.droidux.processor.fixture.Counter;",
                "import info.izumin.android.droidux.processor.fixture.TodoList;",
                "import rx.Observable;",
                "@Store({CounterReducer.class, TodoListReducer.class})",
                "public interface RootStore {",
                "    Counter counter();",
                "    Observable<Counter> observeCounter();",
                "    TodoList todoList();",
                "    Observable<TodoList> observeTodoList();",
                "    Observable<Action> dispatch(Action action);",
                "}"
        };

        public static final String[] GENERATED = {
                "package info.izumin.android.droidux.sample;",
                "",
                "import info.izumin.android.droidux.Action;",
                "import info.izumin.android.droidux.Dispatcher;",
                "import info.izumin.android.droidux.Middleware;",
                "import info.izumin.android.droidux.exception.NotInitializedException;",
                "import info.izumin.android.droidux.processor.fixture.Counter;",
                "import info.izumin.android.droidux.processor.fixture.CounterReducer;",
                "import info.izumin.android.droidux.processor.fixture.TodoList;",
                "import info.izumin.android.droidux.processor.fixture.TodoListReducer;",
                "import java.util.ArrayList;",
                "import java.util.List;",
                "import rx.Observable;",
                "",
                "public final class DroiduxRootStore implements RootStore {",
                "    private final DroiduxRootStore_CounterStoreImpl counterStoreImpl;",
                "    private final DroiduxRootStore_TodoListStoreImpl todoListStoreImpl;",
                "    private final Dispatcher dispatcher;",
                "",
                "    protected DroiduxRootStore(Builder builder) {",
                "        counterStoreImpl= new DroiduxRootStore_CounterStoreImpl(builder.counter, builder.counterReducer);",
                "        todoListStoreImpl= new DroiduxRootStore_TodoListStoreImpl(builder.todoList, builder.todoListReducer);",
                "        dispatcher = new Dispatcher(builder.middlewares, counterStoreImpl, todoListStoreImpl);",
                "    }",
                "",
                "    public Counter counter() {",
                "        return counterStoreImpl.getState();",
                "    }",
                "",
                "    public Observable<Counter> observeCounter() {",
                "        return counterStoreImpl.observe();",
                "    }",
                "",
                "    public TodoList todoList() {",
                "        return todoListStoreImpl.getState();",
                "    }",
                "",
                "    public Observable<TodoList> observeTodoList() {",
                "        return todoListStoreImpl.observe();",
                "    }",
                "",
                "    public Observable<Action> dispatch(Action action) {",
                "        return dispatcher.dispatch(action)",
                "    }",
                "",
                "    public static final class Builder {",
                "        private final List<Middleware> middlewares;",
                "        private CounterReducer counterReducer;",
                "        private TodoListReducer todoListReducer;",
                "        private Counter counter;",
                "        private TodoList todoList;",
                "        ",
                "        public Builder() {",
                "            middlewares = new ArrayList<>();",
                "        }",
                "",
                "        public Builder addMiddleware(Middleware middleware) {",
                "            middlewares.add(middleware);",
                "            return this;",
                "        }",
                "",
                "        public Builder setReducer(CounterReducer counterReducer) {",
                "            this.counterReducer = counterReducer;",
                "            return this;",
                "        }",
                "",
                "        public Builder setReducer(TodoListReducer todoListReducer) {",
                "            this.todoListReducer = todoListReducer;",
                "            return this;",
                "        }",
                "",
                "        public Builder setInitialState(Counter counter) {",
                "            this.counter = counter;",
                "            return this;",
                "        }",
                "",
                "        public Builder setInitialState(TodoList todoList) {",
                "            this.todoList = todoList;",
                "            return this;",
                "        }",
                "",
                "        public DroiduxRootStore build() {",
                "            if (counterReducer == null) {",
                "                throw new NotInitializedException(\"CounterReducer has not been initialized.\");",
                "            }",
                "            if (counter == null) {",
                "                throw new NotInitializedException(\"Counter has not been initialized.\");",
                "            }",
                "            if (todoListReducer == null) {",
                "                throw new NotInitializedException(\"TodoListReducer has not been initialized.\");",
                "            }",
                "            if (todoList == null) {",
                "                throw new NotInitializedException(\"TodoList has not been initialized.\");",
                "            }",
                "            return new DroiduxRootStore(this);",
                "        }",
                "    }",
                "}"
        };
    }

    public static class DispatchableTakesWrongStateType {
        public static final String[] TARGET = {
                "package info.izumin.android.droidux.sample;",
                "import info.izumin.android.droidux.annotation.Dispatchable;",
                "import info.izumin.android.droidux.annotation.Reducer;",
                "import info.izumin.android.droidux.annotation.Store;",
                "import info.izumin.android.droidux.processor.fixture.action.IncrementCountAction;",
                "import info.izumin.android.droidux.processor.fixture.Counter;",
                "@Store(CounterStore.CounterReducer.class)",
                "public interface CounterStore {",
                "    @Reducer(Counter.class)",
                "    public static class CounterReducer {",
                "        @Dispatchable(IncrementCountAction.class)",
                "        public Counter increment(Object state, IncrementCountAction action) {",
                "            return null;",
                "        }",
                "    }",
                "}"
        };
    }

    public static class DispatchableTakesWrongActionType {
        public static final String[] TARGET = {
                "package info.izumin.android.droidux.sample;",
                "import info.izumin.android.droidux.annotation.Dispatchable;",
                "import info.izumin.android.droidux.annotation.Reducer;",
                "import info.izumin.android.droidux.annotation.Store;",
                "import info.izumin.android.droidux.processor.fixture.action.AddTodoItemAction;",
                "import info.izumin.android.droidux.processor.fixture.action.CompleteTodoItemAction;",
                "import info.izumin.android.droidux.processor.fixture.TodoList;",
                "@Store(TodoListStore.TodoListReducer.class)",
                "public interface TodoListStore {",
                "    @Reducer(TodoList.class)",
                "    public static class TodoListReducer {",
                "        @Dispatchable(CompleteTodoItemAction.class)",
                "        public TodoList addItem(TodoList state, AddTodoItemAction action) {",
                "            return null;",
                "        }",
                "    }",
                "}"
        };
    }

    public static class DispatchableTakesExtraArguments {
        public static final String[] TARGET = {
                "package info.izumin.android.droidux.sample;",
                "import info.izumin.android.droidux.annotation.Dispatchable;",
                "import info.izumin.android.droidux.annotation.Reducer;",
                "import info.izumin.android.droidux.annotation.Store;",
                "import info.izumin.android.droidux.processor.fixture.action.IncrementCountAction;",
                "import info.izumin.android.droidux.processor.fixture.Counter;",
                "@Store(CounterStore.CounterReducer.class)",
                "public interface CounterStore {",
                "    @Reducer(Counter.class)",
                "    public static class CounterReducer {",
                "        @Dispatchable(IncrementCountAction.class)",
                "        public Counter increment(Counter state, IncrementCountAction action, String extra) {",
                "            return null;",
                "        }",
                "    }",
                "}"
        };
    }

    public static class DispatchableMethosReturnsWrongType{
        public static final String[] TARGET = {
                "package info.izumin.android.droidux.sample;",
                "import info.izumin.android.droidux.annotation.Dispatchable;",
                "import info.izumin.android.droidux.annotation.Reducer;",
                "import info.izumin.android.droidux.annotation.Store;",
                "import info.izumin.android.droidux.processor.fixture.action.IncrementCountAction;",
                "import info.izumin.android.droidux.processor.fixture.Counter;",
                "@Store(CounterStore.CounterReducer.class)",
                "public interface CounterStore {",
                "    @Reducer(Counter.class)",
                "    public static class CounterReducer {",
                "        @Dispatchable(IncrementCountAction.class)",
                "        public Object increment(Counter state, IncrementCountAction action) {",
                "            return null;",
                "        }",
                "    }",
                "}"
        };
    }

    public static class ReducerWithoutSuffix {
        public static final String[] TARGET = {
                "package info.izumin.android.droidux.sample;",
                "import info.izumin.android.droidux.annotation.Dispatchable;",
                "import info.izumin.android.droidux.annotation.Reducer;",
                "import info.izumin.android.droidux.annotation.Store;",
                "import info.izumin.android.droidux.processor.fixture.action.IncrementCountAction;",
                "import info.izumin.android.droidux.processor.fixture.Counter;",
                "@Store(CounterStore.CounterReduce.class)",
                "public interface CounterStore {",
                "    @Reducer(Counter.class)",
                "    public static class CounterReduce {",
                "        @Dispatchable(IncrementCountAction.class)",
                "        public Counter increment(Counter state, IncrementCountAction action) {",
                "            return null;",
                "        }",
                "    }",
                "}"
        };
    }

    public static class UndoableReducerWithoutUndoableState {
        public static final String[] TARGET = {
                "package info.izumin.android.droidux.sample;",
                "import info.izumin.android.droidux.annotation.Dispatchable;",
                "import info.izumin.android.droidux.annotation.Reducer;",
                "import info.izumin.android.droidux.annotation.Store;",
                "import info.izumin.android.droidux.annotation.Undoable;",
                "import info.izumin.android.droidux.processor.fixture.action.IncrementCountAction;",
                "import info.izumin.android.droidux.processor.fixture.Counter;",
                "@Store(CounterStore.CounterReducer.class)",
                "public interface CounterStore {",
                "    @Undoable",
                "    @Reducer(Counter.class)",
                "    public static class CounterReducer {",
                "    }",
                "}"
        };
    }

    public static class StoreHasInvalidValue {
        public static final String[] TARGET = {
                "package info.izumin.android.droidux.sample;",
                "import info.izumin.android.droidux.annotation.Store;",
                "@Store(CounterStore.CounterReducer.class)",
                "public interface CounterStore {",
                "    public static class CounterReducer {",
                "    }",
                "}"
        };
    }
}
