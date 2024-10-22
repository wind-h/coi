
ApplicationListener
    ApplicationAdapter
        // 首次创建应用程序时调用。
        public void create ();
        // 调整应用程序大小时调用。这可以在非暂停状态下的任何时候发生，但在调用create（）之前永远不会发生。
        public void resize (int width, int height);
        // 渲染
        public void render ();
        // 当应用程序暂停时调用，通常是当它不活动或在屏幕上不可见时。应用程序在销毁之前也会暂停。
        public void pause ();
        // 当应用程序从暂停状态恢复时调用，通常是当它重新获得焦点时。
        public void resume ();
        // 当应用程序被销毁时调用。在调用pause()之前。
        public void dispose ();
