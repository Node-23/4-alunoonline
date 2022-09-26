<%@tag pageEncoding="UTF-8" %>
<%@taglib uri="futurepagesApp" prefix="fpg"%>

<%@attribute name="id"  required="true"%>
<%@attribute name="name"  required="true"%>
<%@attribute name="sourceValue"  required="true"%>
<%@attribute name="url"  required="true"%>
<%@attribute name="cssClass"  required="false"%>
<%@attribute name="defaultText"  required="false"%>
<%@attribute name="defaultValue"  required="false"%>
<%@attribute name="selected"  required="true"%>
<%@attribute name="style"  required="false"%>
<%@attribute name="disabledAfterChange" required="false" %>
<%@attribute name="onchangeSelect" required="false" %>


<fpg:if value="${defaultValue!=null || defaultText!=null}" context="true">
  <fpg:then>
    <fpg:set var="defaultOptions"><option value="${defaultValue!=null ?defaultValue:''}">${defaultText!=null?defaultText:''}</option></fpg:set>
  </fpg:then>
  <fpg:else>
    <fpg:set var="defaultOptions" value=""/>
  </fpg:else>
</fpg:if>
<select id="${id}" style="${style}" name="${name}" class="${cssClass} ajaxSelect"
        <fpg:if value="${onchangeSelect!=null}">
          onchange="${onchangeSelect}"
        </fpg:if>
>${defaultOptions}</select>

<fpg:footer>
  <script type="text/javascript">
    $(function() {
      var selected = '${selected!=null?selected:''}';
      var $ajaxSelect   = $('#${id}');
      var $sourceSelect = $('#${sourceValue}');
      var carregandoOption = '<option value="${defaultValue!=null ?defaultValue:''}">Carregando...</option>';
      var sourceIsAjaxToo = $sourceSelect.hasClass('ajaxSelect');
      $ajaxSelect.attr('disabled', 'disabled');

      $sourceSelect.change(function(){
        $ajaxSelect.attr('disabled', 'disabled');
        if ($sourceSelect.val() && $sourceSelect.val() != '${defaultValue!=null ?defaultValue:''}') {
          $ajaxSelect.html(carregandoOption);
          $.getJSON('<fpg:contextPath/>${url}', {sourceValue: $sourceSelect.val()}, function (j) {
            if (j.obj.length > 0) {
              var options = '${defaultOptions}';
              for (var i = 0; i < j.obj.length; i++) {
                options += '<option value="' + j.obj[i].key + '" style="font-size:0.95em;" >' + j.obj[i].value + '</option>';
              }
              <fpg:if value="${disabledAfterChange==null || !disabledAfterChange}">
              $ajaxSelect.removeAttr('disabled').css('color', '#272727');
              </fpg:if>
              $ajaxSelect.html(options);
            }else{
              $ajaxSelect.html('');
            }
            if (selected != '') {
              $('#${id} option[value="${selected}"]').attr('selected', 'selected').change();
            }
          });
        } else {
          $ajaxSelect.html('');
          $ajaxSelect.attr('disabled', 'disabled').change();
        }
      });

      if($sourceSelect.val() && $sourceSelect.val() != '${defaultValue!=null ?defaultValue:''}' && !sourceIsAjaxToo){
        $sourceSelect.change();
      }
    });
  </script>
</fpg:footer>