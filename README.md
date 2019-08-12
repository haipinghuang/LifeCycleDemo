# LifeCycleDemo
这是一个测试安卓App中View、Fragment、Activity以及它们交叉存在时的生命周期的demo。所谓好记性不如烂笔头，做这个项目的意义在于记录下android不同状态下各个组件的生命周期，方便做开发查阅，做性能优化；第二个就是若是要得知某个具体的生命周期，通过这个项目也可以马上测试。

下面是本人做的一些常用记录：
### Activity生命周期：
	周期1：onCreate-onCreateView-onStart-onPostCreate-onResume-onResumeFragments-onPostResume-onAttachedToWindow-跳转activity-onPause-onStop-onDestroy-onDetachedFromWindow(finish)
	周期2：onCreate-onCreateView-onStart-onPostCreate-onResume-onResumeFragments-onPostResume-onAttachedToWindow-跳转activity-onPause-onSaveInstanceState-onStop(未finish)
	周期3：onRestart-onStart-onResume-onResumeFragments-onPostResume
总结：Activity在finish情况下不会调用onSaveInstanceState，onSaveInstanceState和onDestroy只有其中一个会被调用

### View生命周期:
	MyTextview(构造方法)-setMethod(自定义方法)-onAttachedToWindow-onMeasure-onLayout-onDraw-onDetachedFromWindow
### Activity和View的交叉生命周期：
	onCreate-onCreateView-
	    (View)MyTextview-setMethod-
	onStart-onPostCreate-onResume-onResumeFragments-onPostResume-onAttachedToWindow-
	    (View)onAttachedToWindow-onMeasure-onLayout-onDraw-
	onPause-onStop-onDestroy-
	    (View)onDetachedFromWindow
	onDetachedFromWindow
总结：在onCreate中调用view的自定义方法 会在view的生命周期(onAttachedToWindow-onMeasure-onLayout-onDraw)之前执行
### Fragment生命周期:
	周期1：onAttach-onCreate-onCreateView-onViewCreated-onActivityCreated-onStart-onResume-onPause-onStop-onDestroyView-onDestroy-onDetach
	周期2：onAttach-onCreate-onCreateView-onAttachFragment(有子Fragment)-onViewCreated-onActivityCreated-onStart-onResume-onPause-onStop-onDestroyView-onDestroy-onDetach
### Fragment直接写入xml中，Fragment和Fctivity的交叉生命周期：
	onCreate-onCreateView-
		 (Fragment)onAttach-
	onAttachFragment-
		 (Fragment)onCreate-onCreateView-onViewCreated-onActivityCreated-onStart-
	onStart-onPostCreate-onResume-
		 (Fragment)onResume-
	onResumeFragments-onPostResume-onAttachedToWindow-
		 (Fragment)onPause-
	onPause-
		 (Fragment)onStop-
	onStop-
		 (Fragment)onDestroyView-onDestroy-onDetach-
	onDestroy-onDetachedFromWindow

	单独Fragment周期：
         onAttach-onCreate-onCreateView-onViewCreated-onActivityCreated-onStart-onResume-onPause-onStop(Actiity未finish)
         onStart-onResume
总结：注意Fragment的onResume在Activity的onResume之后执行；Fragment只有执行了onDestory才会指向onDetach
### Fragment在TabHost管理下的生命周期
    onAttach-onCreate-onCreateView-onViewCreated-onActivityCreated-onStart-onResume
    切换到新tab
    onAttach-onCreate-(onPause-onStop-onDestroyView)-onCreateView-onViewCreated-onActivityCreated-onStart-onResume
    切换到旧tab
    (onPause-onStop-onDestroyView)-onCreateView-onViewCreated-onActivityCreated-onStart-onResume
总结：在TabHost管理下一次只会初始化一个Fragment；切换时旧的Fragment会执行onDestroyView，不会执行onDestroy；
初始化过的Fragment都会缓存在FragmentTabHost

### Fragment在FragmentPagerAdapter管理下的生命周期
    setUserVisibleHint-onAttach-onCreate-onCreateView-onViewCreated-onActivityCreated-onStart-onResume
    向右切换超过缓存数量后
    setUserVisibleHint-onAttach-onCreate-(onPause-onStop-onDestroyView)-onCreateView-onViewCreated-onActivityCreated-onStart-onResume
    向左切换超过缓存数量后
    setUserVisibleHint-onCreateView-onViewCreated-onActivityCreated-(onPause-onStop-onDestroyView)-onStart-onResume
总结：setUserVisibleHint永远是第一个调用的方法，初始化时会调用mOffscreenPageLimit(ViewPage预加载数)+2次，后面切换时会调用3次；
setOffscreenPageLimit是设置当前页左右各缓存的页数；在FragmentPagerAdapter管理下默认会初始化两个Fragment；
会缓存(mOffscreenPageLimit\*2+1)个Fragment的View在缓存中,超过这个数的Fragment会执行onDestroyView，但不会执行onDestroy；
FragmentPagerAdapter管理下的所有Fragment都不会执行onDestroy，意味着自己无需通过ArrayList引用Fragment；
FragmentPagerAdapter创建的Fragment实例会一直存在内存中直到Activity执行Finish；

### Fragment与FragmentAciviy在FragmentPagerAdapter管理下的交叉生命周期
    onCreate-onCreateView-onStart-onPostCreate-onResume-onResumeFragments-onPostResume-onAttachedToWindow
         (Fragment)setUserVisibleHint-onAttach-
    onAttachFragment
         (Fragment)onCreate-onCreateView-
    onCreateView
         (Fragment)onViewCreated-onActivityCreated-onStart-onResume-
    缓存满情况下切换ViewPage
         (Fragment)setUserVisibleHint-onAttach-
    onAttachFragment
         (Fragment)onCreate-(onPause-onStop-onDestroyView)-onCreateView-
    onCreateView
         (Fragment)onViewCreated-onActivityCreated-onStart-onResume-

### Fragment在FragmentStatePagerAdapter管理下的生命周期
    setUserVisibleHint-onAttach-onCreate-onCreateView-onViewCreated-onActivityCreated-onStart-onResume
    向右切换超过缓存数量后
    setUserVisibleHint-onAttach-onCreate-(onPause-onStop-onDestroyView-onDestroy-onDetach)-onCreateView-onViewCreated-onActivityCreated-onStart-onResume
    向左切换超过缓存数量后
    setUserVisibleHint-onAttach-onCreate-(onPause-onStop-onDestroyView-onDestroy-onDetach)-onCreateView-onViewCreated-onActivityCreated-onStart-onResume
总结：和FragmentPagerAdapter不同的地方在于：
会缓存(mOffscreenPageLimit\*2+1)个Fragment的View在缓存中,超过这个数的Fragment会执行onDestroy、onDetach；
超过缓存数(mOffscreenPageLimit\*2+1)的Fragment会主动销毁，节约内存；

### Fragment与FragmentAciviy在FragmentStatePagerAdapter管理下的交叉生命周期
	onCreate-onCreateView-onStart-onPostCreate-onResume-onResumeFragments-onPostResume-onAttachedToWindow
	     (Fragment)setUserVisibleHint-onAttach-
	onAttachFragment
	     (Fragment)onCreate-onCreateView-
	onCreateView
	     (Fragment)onViewCreated-onActivityCreated-onStart-onResume-
	缓存满情况下切换ViewPage
	     (Fragment)setUserVisibleHint-onAttach-
	onAttachFragment
	     (Fragment)onCreate-(onPause-onStop-onDestroyView-onDestroy-onDetach)-onCreateView-
	onCreateView
	     (Fragment)onViewCreated-onActivityCreated-onStart-onResume-
