**REST**（Representational State Transfer）,中文翻译叫“表述性状态转移”。是 Roy Thomas Fielding 在他2000年的博士论文中提出的。它与传统的 SOAP Web 服务区别在于，REST关注的是要处理的数据，而 SOAP 主要关注行为和处理。要理解好 REST，根据其首字母拆分出的英文更容易理解。

**表述性**（Representational）:对于 REST 来说，我们网络上的一个个URI资源可以用各种形式来表述，例如：XML、JSON或者HTML等。

**状态**（State）：REST 更关注资源的状态而不是对资源采取的行为。

**转移**（Transfer）：在网络传输过程中，REST 使资源以某种表述性形式从一个应用转移到另一个应用（如从服务端转移到客户端）。

`具体来说，REST 中存在行为，它的行为是通过 HTTP 表示操作的方法来定义的即：GET、POST、PUT、DELETE、PATCH；GET用来获取资源，POST用来新建资源（也可以用于更新资源），PUT用来更新资源，DELETE用来删除资源，PATCH用来更新资源。 基于 REST 这样的观点，我们需要避免使用 REST服务、REST Web服务 这样的称呼，这些称呼多少都带有一些强调行为的味道。`

**使用 RESTful 架构设计使用误区**

RESTful 架构：是基于 REST 思想的时下比较流行的一种互联网软件架构。它结构清晰、符合标准、易于理解、扩展方便，所以正得到越来越多网站的采用。

在没有足够了解 REST 的时候，我们很容易错误的将其视为 “基于 URL 的 Web 服务”，即将 REST 和 SOAP 一样，是一种远程过程调用（remote procedure call，RPC）的机制。但是 REST 和 RPC 几乎没有任何关系，RPC 是面向服务的，而 REST 是面向资源的，强调描述应用程序的事物和名词。这样很容易导致的一个结果是我们在设计 RESTful API 时，在 URI 中使用动词。例如：GET /user/getUser/123。正确写法应该是 GET /user/123。

使用 springMVC 支持 RESTful

在 spring 3.0 以后，spring 这对 springMVC 的一些增强功能对 RESTful 提供了良好的支持。在4.0后的版本中，spring 支持一下方式创建 REST 资源：

控制器可以处理所有的 HTTP 方法，包含几个主要的 REST 方法：GET、POST、PUT、DELETE、PATCH；
借助 @PathVariable 注解，控制器能够处理参数化的 URL（将变量输入作为 URL 的一部分）；
借助 spring 的视图解析器，资源能够以多种方式进行表述，包括将模型数据渲染为 XML、JSON、Atom、已经 RSS 的 View 实现；
可以使用 ContentNegotiatingViewResolver 来选择最适合客户端的表述；
借助 @ResponseBody 注解和各种 HttpMethodConverter 实现，能够替换基于视图的渲染方式；
类似地，@RequestBody 注解以及 HttpMethodConverter 实现可以将传入的 HTTP 数据转化为传入控制器处理方法的 Java 对象；
借助 RestTemplate ，spring 应用能够方便地使用 REST 资源。