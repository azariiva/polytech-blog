<script src="https://code.jquery.com/jquery-3.6.0.min.js"
        integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script src="/static/submitAsJson.js"></script>

<#macro send_message_form>
    <form action="/article/submit" method="post" id="sendMessageForm">
        <div class="input-group mb-3">
            <div class="list-group flex-grow-1">
                <textarea class="list-group-item form-control" rows="1" name="title" placeholder="Title"
                          required="true"></textarea>
                <textarea class="list-group-item form-control" rows="3" name="body" placeholder="Your nano text"
                          required="true"></textarea>
            </div>
            <input class="btn btn-dark" type="submit" value="Send! 🚀">
        </div>
    </form>
    <script type="text/javascript">
        $("#sendMessageForm").submit(function (event) {
            event.preventDefault();
            submitAsJSON($(this))
        })
    </script>
</#macro>