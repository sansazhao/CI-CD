# CI-CD
[![Build Status](https://travis-ci.org/zhaoying98sjtu/CI-CD.svg?branch=master)](https://travis-ci.org/zhaoying98sjtu/CI-CD)
## CI是什么？
### CONTINUOUS INTEGRATION  持续集成
 在持续集成环境中，开发人员将会频繁的提交代码到主干。这些新提交在最终合并到主线之前，都需要通过编译和自动化测试流进行验证。这样做是基于之前持续集成过程中很重视自动化测试验证结果，以保障所有的提交在合并主线之后的质量问题，对可能出现的一些问题进行预警。

### 持续集成需要什么条件？
+ 需要为每个新功能，代码改进，或者问题修复创建自动化测试用例

+ 需要一个持续集成服务器，它可以监控代码提交情况，对每个新的提交进行自动化测试

+ 研发团队需要尽可能快的提交代码，至少每天一次提交

### 持续集成有什么好处？
+ 通过自动化测试可以提早拿到回归测试的结果，避免将一些问题提交到交付生产中

+ 发布编译将会更加容易，因为合并之初已经将所有问题都规避了

+ 减少工作问题切换，研发可以很快获得构建失败的消息，在开始下一个任务之前就可以很快解决

+ 测试成本大幅降低-你的CI服务器可以在几秒钟之内运行上百条测试

+ QA团队花费在测试上面的时间会大幅缩短，将会更加侧重于质量文化的提升上面

## CD是什么？
### CONTINUOUS DELIVERY 持续交付
持续交付就是讲我们的应用发布出去的过程。这个过程可以确保我们尽可能快的实现交付。这就意味着除了自动化测试，我们还需要有自动化的发布流，以及通过一个按键就可以随时随地实现应用的部署上线。

### 持续交付需要什么条件？
+ 需要有强大的持续集成组件和足够多的测试项可以满足代码的需求

+ 部署需要自动化。触发是手动的，但是部署一旦开始，就不能人为干预

+ 可能需要接受特性开关，没有完成的功能模块不会影响到线上产品

### 持续交付有什么好处？
+ 繁琐的部署工作没有了。不再需要花费几天的时间去准备一个发布

+ 可以更快的进行交付，这样就加快了与客户之间的反馈环

+ 轻松应对小变更，加速迭代

### CONTINUOUS DEPLOYMENT 持续部署
通过这个方式，任何修改通过了所有已有的工作流就会直接和客户见面。没有人为干预（没有一键部署按钮），只有当一个修改在工作流中构建失败才能阻止它部署到产品线

### 持续部署需要什么条件？
+ 研发团队测试理念比较完善。测试单元的健壮性直接决定你的交付质量

+ 文档和部署频率要保持一致

+ 特征标志成为发布重大变化过程的固有部分，以确保可以与其他部门（支持，市场营销，公关…）协调

### 持续部署有什么好处？
+ 发布频率更快，因为不需要停下来等待发布。每一处提交都会自动触发发布流

+ 在小批量发布的时候，风险降低了，发现问题也可以很轻松的修复

+ 客户每天都可以看到我们的持续改进和提升，而不是每个月或者每季度，或者每年


## 我们使用了什么工具？

### travis-ci
![logo](https://cdn.stephencode.com/article/travis/travis.jpg)

Travis CI 提供的是持续集成服务。它绑定 Github 上面的项目，只要有新的代码，就会自动抓取。然后，提供一个运行环境，执行测试，完成构建，还能部署到服务器。

#### 使用条件
+ 拥有 GitHub 帐号
+ 该帐号下面有一个项目
+ 该项目里面有可运行的代码
+ 该项目还包含构建或测试脚本

#### 如何使用
1. 创建maven项目，使用Junit进行单元测试
2. 在github项目的根目录新建文件：.travis.yml
3. 参考Travis官方文档，配置language,jdk等信息
4. 高级自定义配置：自己编写before/after scripts的命令
