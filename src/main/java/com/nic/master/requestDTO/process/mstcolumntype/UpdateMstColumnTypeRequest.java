    package com.nic.master.requestDTO.process.mstcolumntype;

    import lombok.Data;

    @Data
    public class UpdateMstColumnTypeRequest {

        private String columnTypeCode;

        private String defaultLabel;

        private String modifiedRemarks;

        private String modifiedUri;

        public String getColumnTypeCode() {
            return columnTypeCode;
        }

        public void setColumnTypeCode(String columnTypeCode) {
            this.columnTypeCode = columnTypeCode;
        }

        public String getDefaultLabel() {
            return defaultLabel;
        }

        public void setDefaultLabel(String defaultLabel) {
            this.defaultLabel = defaultLabel;
        }

        public String getModifiedRemarks() {
            return modifiedRemarks;
        }

        public void setModifiedRemarks(String modifiedRemarks) {
            this.modifiedRemarks = modifiedRemarks;
        }

        public String getModifiedUri() {
            return modifiedUri;
        }

        public void setModifiedUri(String modifiedUri) {
            this.modifiedUri = modifiedUri;
        }
    }
