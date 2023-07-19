<template>
  <div>
    <h2 id="page-heading" data-cy="BlogHeading">
      <span v-text="$t('myApp.blog.home.title')" id="blog-heading">Blogs</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('myApp.blog.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'BlogCreate' }" custom v-slot="{ navigate }">
          <button @click="navigate" id="jh-create-entity" data-cy="entityCreateButton" class="btn btn-primary jh-create-entity create-blog">
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('myApp.blog.home.createLabel')"> Create a new Blog </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && blogs && blogs.length === 0">
      <span v-text="$t('myApp.blog.home.notFound')">No blogs found</span>
    </div>
    <template>
      <search-entity search-bean-name="blogDAO"> </search-entity>
    </template>
    <b-modal ref="removeEntity" id="removeEntity">
      <span slot="modal-title"
        ><span id="myApp.blog.delete.question" data-cy="blogDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-blog-heading" v-text="$t('myApp.blog.delete.question', { id: removeId })">
          Are you sure you want to delete this Blog?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-blog"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeBlog()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./blog.component.ts"></script>
